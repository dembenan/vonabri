

/*
 * Java transformer for entity table travailleur 
 * Created on 2022-06-11 ( Time 19:24:50 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.palmafrique.vonabri.rest.api;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ci.palmafrique.vonabri.business.TravailleurBusiness;
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
	private FunctionalError functionalError;

	@Autowired
	private ExceptionUtils			exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
	
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
}
