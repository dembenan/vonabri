

/*
 * Java transformer for entity table travailleur 
 * Created on 2022-06-11 ( Time 19:24:50 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.palmafrique.vonabri.rest.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ci.palmafrique.vonabri.business.TravailleurBusiness;
import ci.palmafrique.vonabri.dao.entity.Element;
import ci.palmafrique.vonabri.dao.repository.ElementRepository;
import ci.palmafrique.vonabri.excel.ExcelGenerator;
import ci.palmafrique.vonabri.jwt.JwtTokenUtil;
import ci.palmafrique.vonabri.utils.CloudinaryService;
import ci.palmafrique.vonabri.utils.ExceptionUtils;
import ci.palmafrique.vonabri.utils.FileStorageProperties;
import ci.palmafrique.vonabri.utils.FileStorageService;
import ci.palmafrique.vonabri.utils.FunctionalError;
import ci.palmafrique.vonabri.utils.StatusCode;
import ci.palmafrique.vonabri.utils.StatusMessage;
import ci.palmafrique.vonabri.utils.Validate;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.utils.dto.TravailleurDto;



/**
Controller for table "travailleur"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/travailleur")
public class TravailleurController {

	@Autowired
	private TravailleurBusiness travailleurBusiness;
	@Autowired
	private Cloudinary cloudinary;
	@Autowired
	private UserController userController;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private ExceptionUtils			exceptionUtils;

	@Autowired
	private ExcelGenerator excelGenerator;
	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private HttpServletRequest requestBasic;
	
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private FileStorageProperties fileStorageProperties;



	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravailleurDto> create(@RequestBody Request<TravailleurDto> request) {
    	slf4jLogger.info("start method /travailleur/create");
        Response<TravailleurDto> response = new Response<TravailleurDto>();
        
        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = travailleurBusiness.create(request, locale);
        	}else{
        	   slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
        	   return response;
        	}
        	
        	if(!response.isHasError()){
				response.setStatus(functionalError.SUCCESS("", locale));
        	    slf4jLogger.info("end method create");
          	    slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);  
            }else{
             	slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
            }
 
        } catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /travailleur/create");
        return response;
    }
	
	@RequestMapping(value = "/creationEnMasse", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public Response<TravailleurDto> creationEnMasse(@RequestParam("file") MultipartFile file,@RequestParam("user") Integer user) throws IOException {
		Response<TravailleurDto> response = new Response<TravailleurDto>();
		slf4jLogger.info("end method /travailleur/upload/creationEnMasse");
		Locale locale = new Locale("fr");
		// repertoire de sauvegarde du fichier uploadé
		try {

			String fileName = fileStorageService.storeFile(file);
			Path pathDest = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
			String pathDestNormalize = pathDest.toString();
			pathDestNormalize += "/" + fileName;
			String file_name_full = pathDestNormalize;
			
			Request<TravailleurDto> request = new Request<TravailleurDto>();
			request.setUser(user);
			
			response = travailleurBusiness.creationEnMasse(request,file_name_full);
			System.out.println(" ---> response = " + response);
			if (!response.isHasError()) {
				slf4jLogger.info("end method travailleur upload");
				slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				response.setHasError(true);
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		slf4jLogger.info("end method /travailleur/upload/creationEnMasse");
		return response;
	}
	
	@GetMapping("/export/template")
	public String downloadExcelFile() throws IOException, ParseException {
		Element element = elementRepository.findByCode("CREATION_TRAVAILLEURS_TEMPLATE", true);
		if(element == null) {
			return "ELEMENT NON TROUVE";
		}
		String link = null;
		String[] headers = element.getChampsCreation().split(",");
		try {
			//Create workbook in .xlsx format
			Workbook workbook = new XSSFWorkbook();
			//For .xsl workbooks use new HSSFWorkbook();
			//Create Sheet
			Sheet sheet = workbook.createSheet("CREATION_TRAVAILLEURS_TEMPLATE");
			//Create top row with column headings
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short)12);
			headerFont.setColor(IndexedColors.WHITE.index);
			//Create a CellStyle with the font
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			//headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			headerStyle.setFillForegroundColor(IndexedColors.BLACK.index);
			headerStyle.setBorderBottom(BorderStyle.THICK);
			headerStyle.setBorderTop(BorderStyle.THICK);
			headerStyle.setBorderLeft(BorderStyle.THICK);
			headerStyle.setBorderRight(BorderStyle.THICK);
			//Create the header row
			Row headerRow = sheet.createRow(0);
			headerRow.setHeightInPoints(30);
			//Iterate over the column headings to create columns
			for(int i=0;i<headers.length;i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
			}
			//Freeze Header Row
//			sheet.createFreezePane(0, 1);
//			int rownum =2;
//			for(CallApiDto i : callApiDtosWithError) {
//				//System.out.println("rownum-before"+(rownum));
//				Row row = sheet.createRow(rownum++);
//				//System.out.println("rownum-after"+(rownum));
//				row.createCell(0).setCellValue(i.getSubscriber());
//				row.createCell(1).setCellValue(i.getIpAddress());
//				row.createCell(2).setCellValue(i.getNd());
//				row.createCell(3).setCellValue(i.getOffer());
//				row.createCell(4).setCellValue(i.getOntMacLabel());
//				row.createCell(5).setCellValue(i.getDslam());
//			}
			

			
			
			//Autosize columns
			for(int i=0;i<headers.length;i++) {
				sheet.autoSizeColumn(i);
			}
			File currDir = new File(fileStorageProperties.getUploadDir());
			String path = currDir.getAbsolutePath();
			
			String fileLocation = path+"/CREATION_TRAVAILLEURS_TEMPLATE.xlsx";
			
			System.out.println("FILE path path=====>"+path + "/CREATION_TRAVAILLEURS_TEMPLATE.xlsx");

			System.out.println("FILE LOCATION"+fileLocation);
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
			
			
			Path pathDest = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
			String pathDestNormalize = pathDest.toString();
			pathDestNormalize += "/" + "CREATION_TRAVAILLEURS_TEMPLATE";
			String file_name_full = pathDestNormalize;
			
			File f = new File(fileLocation);
	        System.out.println("File NAME =====>: " + f.getName());
	        System.out.println("File getAbsoluteFile =====>: " + f.getAbsoluteFile() );

	        Map result = cloudinary.uploader().upload(f, ObjectUtils.asMap("use_filename", "true","unique_filename", "false","resource_type", "auto"));
	        f.delete();
            link = (String) result.get("secure_url");
        	element.setIcon(link);
        	elementRepository.save(element);
	        System.out.println("result =====>: " + result);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return link;

        
	}
	
//	@GetMapping("/export/template")
//	public String downloadExcelFile() throws IOException, ParseException {
//		Element element = elementRepository.findByCode("CREATION_TRAVAILLEURS_TEMPLATE", true);
//		if(element == null) {
//			return "ELEMENT NON TROUVE";
//		}
//	    ExportExcel exportExcel = new ExportExcel(element);
//        String result = exportExcel.getExcelFileTemplate();
//		String[] headers = element.getChampsCreation().split(",");
//		for (int i = 0; i < headers.length; i++) {
//			System.out.println("COLLUM " + i + "====>" + headers[i]);
//		}
//        return result;
//
//  
//	}
	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravailleurDto> update(@RequestBody Request<TravailleurDto> request) {
    	slf4jLogger.info("start method /travailleur/update");
        Response<TravailleurDto> response = new Response<TravailleurDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");
        
		try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = travailleurBusiness.update(request, locale);
        	}else{
        	   slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
        	   return response;
        	}
        	
        	if(!response.isHasError()){
				response.setStatus(functionalError.SUCCESS("", locale));
        	  	slf4jLogger.info("end method update");
          	  	slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);  
            }else{
              	slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
            }
 
        } catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /travailleur/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravailleurDto> delete(@RequestBody Request<TravailleurDto> request) {
    	slf4jLogger.info("start method /travailleur/delete");
        Response<TravailleurDto> response = new Response<TravailleurDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = travailleurBusiness.delete(request, locale);
        	}else{
        	   slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
        	   return response;
        	}
        	
        	if(!response.isHasError()){
				response.setStatus(functionalError.SUCCESS("", locale));
        	  	slf4jLogger.info("end method delete");
          	  	slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);  
            }else{
              slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
            }
 
        } catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /travailleur/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravailleurDto> getByCriteria(@RequestBody Request<TravailleurDto> request) {
    	slf4jLogger.info("start method /travailleur/getByCriteria");
        Response<TravailleurDto> response = new Response<TravailleurDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateObject(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = travailleurBusiness.getByCriteria(request, locale);
        	}else{
        	   slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
        	   return response;
        	}
        	
        	if(!response.isHasError()){
        	  	slf4jLogger.info("end method getByCriteria");
          	  	slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);  
            }else{
              	slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
            }
 
        } catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /travailleur/getByCriteria");
        return response;
    }
	
	@RequestMapping(value="/upload",method=RequestMethod.POST,consumes = {"multipart/form-data" }, produces = { "application/json" })
    public Response<TravailleurDto> uploadPhoto(@RequestParam("file") MultipartFile file,@RequestParam("user") Integer user) {
		
		

		slf4jLogger.info("start method /travailleur/photo");
		
        Response<TravailleurDto> response = new Response<TravailleurDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {
        	Request<TravailleurDto> request = new Request<TravailleurDto>();
        	request.setUser(user);
        	request.setFile(file);
            response = travailleurBusiness.uploadPhoto(request, locale);
 
        } catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /travailleur/photo");
        return response;
    }
}
