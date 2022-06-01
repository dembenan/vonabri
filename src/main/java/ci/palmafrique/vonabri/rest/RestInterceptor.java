
/*
 * Created on 2022-04-25 ( Time 03:19:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.palmafrique.vonabri.rest;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;

import ci.palmafrique.vonabri.utils.contract.Response;

public class RestInterceptor extends HandlerInterceptorAdapter {

	private static String	defaultTenant	= "null";
	
	private static String defaultLanguage = "fr";

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		String tenantValue = req.getHeader("tenantID");

		if (tenantValue != null) {
			req.setAttribute("CURRENT_TENANT_IDENTIFIER", tenantValue);
		} else {
			req.setAttribute("CURRENT_TENANT_IDENTIFIER", defaultTenant);
		}
		
		String langValue = req.getHeader("lang");

		if (langValue != null) {
			req.setAttribute("CURRENT_LANGUAGE_IDENTIFIER", langValue);
		} else {
			req.setAttribute("CURRENT_LANGUAGE_IDENTIFIER", defaultLanguage);
		}

		return false;
	}
	public String ReturnAccesDenied(HttpServletRequest req, HttpServletResponse res) {
		String responseValue = "";
		try {
			Response respObj = new Response();
			respObj.setHasError(Boolean.TRUE);
			String lang = req.getHeader("lang");
			Locale locale = null;
			if (lang != null) {
				locale = new Locale(lang, "");
			} else {
				locale = new Locale("en", "");
			}
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			respObj.setCode(HttpServletResponse.SC_UNAUTHORIZED);
			respObj.setMessage("Access denied");

//			Status status = new Status();
//			status.setCode(StatusCode.FUNC_ACCESS_DENIED);
//			status.setMessage(StatusMessage.FUNC_ACCESS_DENIED);
//			respObj.setStatus(status);
			responseValue = new Gson().toJson(respObj, Response.class);
			res.setContentType(MediaType.APPLICATION_JSON_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseValue;
	}
	
}