

/*
 * Java transformer for entity table type_mariage 
 * Created on 2022-06-11 ( Time 19:24:50 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.palmafrique.vonabri.rest.api;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RestController;

import ci.palmafrique.vonabri.utils.dto.*;
import ci.palmafrique.vonabri.utils.contract.*;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.utils.*;
import ci.palmafrique.vonabri.business.*;


/**
Controller for table "type_mariage"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/typeMariage")
public class TypeMariageController {

	@Autowired
	private TypeMariageBusiness typeMariageBusiness;

	@Autowired
	private FunctionalError functionalError;

	@Autowired
	private ExceptionUtils			exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HttpServletRequest requestBasic;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeMariageDto> create(@RequestBody Request<TypeMariageDto> request) {
    	slf4jLogger.info("start method /typeMariage/create");
        Response<TypeMariageDto> response = new Response<TypeMariageDto>();
        
        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = typeMariageBusiness.create(request, locale);
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
		slf4jLogger.info("end method /typeMariage/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeMariageDto> update(@RequestBody Request<TypeMariageDto> request) {
    	slf4jLogger.info("start method /typeMariage/update");
        Response<TypeMariageDto> response = new Response<TypeMariageDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");
        
		try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = typeMariageBusiness.update(request, locale);
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
		slf4jLogger.info("end method /typeMariage/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeMariageDto> delete(@RequestBody Request<TypeMariageDto> request) {
    	slf4jLogger.info("start method /typeMariage/delete");
        Response<TypeMariageDto> response = new Response<TypeMariageDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = typeMariageBusiness.delete(request, locale);
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
		slf4jLogger.info("end method /typeMariage/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeMariageDto> getByCriteria(@RequestBody Request<TypeMariageDto> request) {
    	slf4jLogger.info("start method /typeMariage/getByCriteria");
        Response<TypeMariageDto> response = new Response<TypeMariageDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateObject(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = typeMariageBusiness.getByCriteria(request, locale);
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
		slf4jLogger.info("end method /typeMariage/getByCriteria");
        return response;
    }
}
