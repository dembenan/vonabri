package ci.palmafrique.vonabri.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloudinary.Cloudinary;

import ci.palmafrique.vonabri.dao.entity.Element;
import ci.palmafrique.vonabri.dao.repository.ElementRepository;
import ci.palmafrique.vonabri.utils.CloudinaryService;
import ci.palmafrique.vonabri.utils.FileStorageProperties;
import ci.palmafrique.vonabri.utils.FileStorageService;
import ci.palmafrique.vonabri.utils.Utilities;



public class ExportExcel {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private Cloudinary cloudinary;
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private FileStorageProperties fileStorageProperties;
	//private List<Travailleur> listTravailleur;
	private String[] headerTemplateCreationTravailleur;
	private Element element;
	
	
	public ExportExcel(Element element) {
		//this.listTravailleur=list;
		this.element = element;
	
		workbook = new XSSFWorkbook();
	    // Resize all columns to fit the content size
	}
	
	private void createCell(Row row,int columnCount, Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long) {
			cell.setCellValue((Long) value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeHeaderLine(String fileName, String title) throws IOException {
		sheet = workbook.createSheet(fileName);


		Row row = sheet.createRow(0);
		CellStyle titlestyle = workbook.createCellStyle();
		XSSFFont titlefont = workbook.createFont();
		titlefont.setBold(true);
		titlefont.setFontHeight(25);
		titlefont.setColor(IndexedColors.GREEN.getIndex());
		titlestyle.setFont(titlefont);
		//style.setAlignment(HorizontalAlignment.CENTER);
		//style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		createCell(row, 0, title, titlestyle);

		XSSFFont headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeight(25);
		//headerFont.setFontHeightInPoints((short)25);
		headerFont.setColor(IndexedColors.BLACK.index);
		//Create a CellStyle with the font
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
//		
//		
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
//		font.setFontHeightInPoints((short) (10));
		row = sheet.createRow(1);
		headerFont.setBold(true);
		headerFont.setFontHeight(25);
		headerFont.setColor(IndexedColors.WHITE.index);
		headerStyle.setFillForegroundColor(IndexedColors.BLACK.index);
		headerStyle.setFont(headerFont);
		String champs = element.getChampsCreation();
		if(Utilities.notBlank(champs)) {
			String[] headers = champs.split(",");
			for (int i = 0; i < headers.length; i++) {
				System.out.println("COLLUM " + i + "====>" + headers[i]);
				createCell(row, i, headers[i], headerStyle);
				sheet.autoSizeColumn(i);
			}
		}

		File currDir = new File(fileStorageProperties.getUploadDir());
		String path = currDir.getAbsolutePath();
		
		String fileLocation = path+"/CREATION_TRAVAILLEURS_TEMPLATE.xlsx";
		
		System.out.println("FILE path path=====>"+path + "/CREATION_TRAVAILLEURS_TEMPLATE.xlsx");

		System.out.println("FILE LOCATION"+fileLocation);
		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();
	}
	
//	void autoSizeColumn(int columnIndex) {
//		  sheet.autoSizeColumn(columnIndex);
//	 }
//	private void writeDataLines() {
//		int rowCount=2;
//		
//		CellStyle style=workbook.createCellStyle();
//		XSSFFont font=workbook.createFont();
//		font.setFontHeight(14);
//		style.setFont(font);
//		
//		for(Travailleur cl:listTravailleur) {
//			Row row=sheet.createRow(rowCount++);
//			int columnCount=0;
//			createCell(row, columnCount++, cl.getPhoneNumber(), style);
//			createCell(row, columnCount++, cl.getMerchantName(), style);
//			createCell(row, columnCount++, cl.getTypeClient().getDescription(), style);
////			createCell(row, columnCount++, cl.get, style);
////			createCell(row, columnCount++, stu.getSpin(), style);
//		}
//	}
//
	public String getExcelFileTemplate() throws IOException {
		File currDir = new File(fileStorageProperties.getUploadDir());
		String path = currDir.getAbsolutePath();
		
		String fileLocation = path+"/CREATION_TRAVAILLEURS_TEMPLATE.xlsx";
				
		File f = new File(fileLocation);
        System.out.println("File NAME =====>: " + f.getName());
        System.out.println("File getAbsoluteFile =====>: " + f.getAbsoluteFile() );
        Map result = cloudinaryService.uploadFile(f);	
        if(result != null) {
        	String link = (String) result.get("secure_url");
        	element.setIcon(link);
        	elementRepository.save(element);
        	return link;
        }else {
        	return "UNE ERREUR S'EST PRODUITE";
        }
        
	}
	

//	public void exportTemplateCreationTravailleur(HttpServletResponse response) throws IOException{
//		writeHeaderLine("TemplateCreationTravailleur","CREATION EN MASSES DES TRAVAILLEURS");
//		//writeDataLines();
//		
//		ServletOutputStream outputStream=response.getOutputStream();
//		workbook.write(outputStream);
//		sheet.
//		workbook.close();
//		outputStream.close();
//	}
	
	
}
