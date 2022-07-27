package ci.palmafrique.vonabri.excel;


import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

@Service
public class ExcelGenerator {
	
	// Excel work book
	private HSSFWorkbook workbook;
	
	// Fonts
	private HSSFFont headerFont;
	private HSSFFont contentFont;
	private HSSFFont totalFont;
	
	// Styles
	private HSSFCellStyle headerStyle;
	private HSSFCellStyle oddRowStyle;
	private HSSFCellStyle evenRowStyle;
	private HSSFCellStyle totalRowStyle;
	
	// Integer to store the index of the next row
	private static int rowIndex;
	
	
	/**
	 * Make a new excel workbook with sheet that contains a stylized table
	 * 
	 * @return
	 */
	public  HSSFWorkbook generateExcel(String[] headerValues,String sheetName) {
		
		// Initialize rowIndex
		rowIndex = 0;
		
		// New Workbook
		workbook = new HSSFWorkbook();
		
		// Generate fonts
		headerFont  = createFont(IndexedColors.WHITE.index, (short)12, false);
		contentFont = createFont(IndexedColors.BLACK.index, (short)10, false);
		totalFont   = createFont(IndexedColors.LIME.index,  (short)10, true);
		
		// Generate styles
		headerStyle   = createStyle(headerFont, IndexedColors.GREY_80_PERCENT.index, true,  IndexedColors.WHITE.index);
		oddRowStyle   = createStyle(contentFont,  IndexedColors.WHITE.index, true,  IndexedColors.GREY_80_PERCENT.index);
		evenRowStyle  = createStyle(contentFont,IndexedColors.GREY_25_PERCENT.index, true,  IndexedColors.GREY_80_PERCENT.index);
		totalRowStyle = createStyle(totalFont,  IndexedColors.GREY_50_PERCENT.index, false, (short)0);
		
		// New sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// Table header
		HSSFRow headerRow = sheet.createRow(rowIndex++ );		
		HSSFCell headerCell = null;
		for (int i = 0; i < headerValues.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue( headerValues[i]);
		}
		
		
		// Table content
//		HSSFRow  contentRow  = null;
//		HSSFCell contentCell = null;
		
		// Obtain table content values
//		List<List<Object>> contentRowValues = FakeDataProvider.getTableContent(20);
//		for (List<Object> rowValues : contentRowValues) {
//			
//			// At each row creation, rowIndex must grow one unit
//			contentRow = sheet.createRow( rowIndex++ );
//			for (int i = 0; i < rowValues.size(); i++) {
//				contentCell = contentRow.createCell(i);
//				
//				// Style depends on if row is odd or even
//				contentCell.setCellStyle( rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle );
//				
//				Object cellValue = rowValues.get(i);
//				if (cellValue instanceof Number) {
//					contentCell.setCellType( HSSFCell.CELL_TYPE_NUMERIC );
//					contentCell.setCellValue( Double.valueOf( cellValue.toString() ).doubleValue() );
//				} else {
//					contentCell.setCellValue((String)cellValue);
//				}
//			}
//			
//			// The last cell of each row contains the formula PRICE_PER_UNIT * UNITS
//			contentCell = contentRow.createCell(3);
//			contentCell.setCellStyle( rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle );
//			contentCell.setCellFormula("B" + rowIndex + "*C" + rowIndex);
//		}
		
		// At last, a row with the order's total
//		HSSFRow totalRow = sheet.createRow( rowIndex++ );
//		HSSFCell totalCell = totalRow.createCell(0);
//		totalCell.setCellStyle(totalRowStyle);
//		
//		totalCell = totalRow.createCell(1);
//		totalCell.setCellStyle(totalRowStyle);
//		
//		totalCell = totalRow.createCell(2);
//		totalCell.setCellValue("Total:");
//		totalCell.setCellStyle(totalRowStyle);
//		
//		// The last cell contains the sum of all sub-totals
//		totalCell = totalRow.createCell(3);
//		totalCell.setCellStyle(totalRowStyle);
//		totalCell.setCellFormula("SUM(D2:D" + (rowIndex - 1) + ")");
		
		// Autosize columns
		for (int i = 0; i < headerValues.length; sheet.autoSizeColumn(i++));
		
		return workbook;
	}
	
	private HSSFFont createFont(short fontColor, short fontHeight, boolean fontBold) {
		
		HSSFFont font = workbook.createFont();
		font.setBold(fontBold);
		font.setColor(fontColor);
		font.setFontName("Arial");
		font.setFontHeightInPoints(fontHeight);
		
		return font;
	}

	private HSSFCellStyle createStyle(HSSFFont font,short cellColor, boolean cellBorder, short cellBorderColor) {

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		//style.setAlignment(()cellAlign);
		style.setFillForegroundColor(cellColor);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (cellBorder) {
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
			
			style.setTopBorderColor(cellBorderColor);
			style.setLeftBorderColor(cellBorderColor);
			style.setRightBorderColor(cellBorderColor);
			style.setBottomBorderColor(cellBorderColor);
		}
		
		return style;
	}
}