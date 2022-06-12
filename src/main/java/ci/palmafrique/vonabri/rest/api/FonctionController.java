

/*
 * Java transformer for entity table fonction 
 * Created on 2022-06-11 ( Time 19:24:47 )
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
Controller for table "fonction"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/fonction")
public class FonctionController {

	@Autowired
	private FonctionBusiness fonctionBusiness;

	@Autowired
	private FunctionalError functionalError;

	@Autowired
	private ExceptionUtils			exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HttpServletRequest requestBasic;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionDto> create(@RequestBody Request<FonctionDto> request) {
    	slf4jLogger.info("start method /fonction/create");
        Response<FonctionDto> response = new Response<FonctionDto>();
        
        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = fonctionBusiness.create(request, locale);
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
		slf4jLogger.info("end method /fonction/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionDto> update(@RequestBody Request<FonctionDto> request) {
    	slf4jLogger.info("start method /fonction/update");
        Response<FonctionDto> response = new Response<FonctionDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");
        
		try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = fonctionBusiness.update(request, locale);
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
		slf4jLogger.info("end method /fonction/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionDto> delete(@RequestBody Request<FonctionDto> request) {
    	slf4jLogger.info("start method /fonction/delete");
        Response<FonctionDto> response = new Response<FonctionDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = fonctionBusiness.delete(request, locale);
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
		slf4jLogger.info("end method /fonction/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionDto> getByCriteria(@RequestBody Request<FonctionDto> request) {
    	slf4jLogger.info("start method /fonction/getByCriteria");
        Response<FonctionDto> response = new Response<FonctionDto>();

        String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {

        	response = Validate.validateObject(request, response, functionalError, locale);
        	if(!response.isHasError()){
               response = fonctionBusiness.getByCriteria(request, locale);
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
		slf4jLogger.info("end method /fonction/getByCriteria");
        return response;
    }
}
