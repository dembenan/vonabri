package ci.palmafrique.vonabri.jwt;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import ci.palmafrique.vonabri.utils.FunctionalError;
import ci.palmafrique.vonabri.utils.Utilities;
import ci.palmafrique.vonabri.utils.contract.Response;


@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private FunctionalError functionalError;


	
	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
	Authentication authentication;
//	    @Autowired
//	    private AuthenticationService authenticationService;

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		// TODO Auto-generated method stub
		String resp = "";
		String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI().toString();
		String origin = (String) request.getHeader("Origin");
		if (origin != null && !origin.isEmpty()) {
			response.setHeader("Access-Control-Allow-Origin", origin);
		} else {
			response.setHeader("Access-Control-Allow-Origin", "*");
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, access-control-allow-methods,Access-Control-Allow-Methods,Accept, X-Requested-With, Content-Type,Access-Control-Allow-Origin, Access-Control-Request-Method, Access-Control-Request-Headers, Show-Success-Message, Show-Loader, Show-Error-Message,sessionUser,lang,user,token,Authorization,content-type,timeout");

       // do your logic
		// FunctionalError functionalError = new FunctionalError();
		
		if ((jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) ) {
			if(uri.contains("user/login")) {
				filterChain.doFilter(request, response);
				return;
			}else {
				resp = ReturnAccesDenied(req, res);
				OutputStream outputStream = res.getOutputStream();
				outputStream.write(resp.getBytes());
				outputStream.flush();
				outputStream.close();
//				response.setStatus(HttpServletResponse.SC_CONFLICT);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		
//		
//		String jwtToken = jwt.substring(7);
//		
//		slf4jLogger.info("token : " + jwtToken);
//		
//		Boolean isTrue = jwtTokenUtil.validateToken(jwtToken);
//		
//		if(Utilities.isTrue(isTrue)) {
//			Jws<Claims> claims = jwtTokenUtil.validateJwtToken(jwtToken);
//			Authentication authentication = authenticationService.getAuthentication(claims);
//             SecurityContextHolder.getContext().setAuthentication(authentication);
////		        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, null,authorities);
////		        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//		        
//		        filterChain.doFilter(request,response);
//	             SecurityContextHolder.getContext().setAuthentication(null);
//		}
		// get only the Token
		
		String jwtToken = extractJwtFromRequest(request,response);
		slf4jLogger.info("token : " + jwtToken);
		
		
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		//Boolean isValidateTime = jwtTokenUtil.validateTokenTime(jwtToken);
		Boolean isValidateTime = Boolean.TRUE;

		System.out.println("boolean isValidateTime ====>" + isValidateTime);

		if(isValidateTime) {
		// Jws<Claims> claims = jwtTokenUtil.validateJwtToken(jwtToken);
		Boolean isValid = jwtTokenUtil.validateToken(jwtToken);
		if (StringUtils.hasText(jwtToken) && Utilities.isTrue(isValid)) {
			UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken), "", new ArrayList<>());
			System.out.println("username " + jwtTokenUtil.getUsernameFromToken(jwtToken));
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			// After setting the Authentication in the context, we specify
			// that the current user is authenticated. So it passes the
			// Spring Security Configurations successfully.
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			


		}
		} else {
			if(!isValidateTime) {
				resp = returnSessionExpired(req, res);
				OutputStream outputStream = res.getOutputStream();
				outputStream.write(resp.getBytes());
				outputStream.flush();
				outputStream.close();
				
				response.setStatus(HttpServletResponse.SC_CONFLICT);
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			}
			System.out.println("Cannot set the Security Context");
		}
		filterChain.doFilter(request, response);
	}

	private String extractJwtFromRequest(HttpServletRequest request,HttpServletResponse httpServletResponse) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}else {
			httpServletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}

	public String returnSessionExpired(HttpServletRequest req, HttpServletResponse res) {
		String responseValue = "";
		try {
			Response respObj = new Response();
			respObj.setHasError(Boolean.TRUE);
			Locale locale = null;
			String lang = req.getHeader("lang");
			if (lang != null) {
				locale = new Locale(lang, "");
			} else {
				locale = new Locale("en", "");
			}
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			respObj.setCode(HttpServletResponse.SC_UNAUTHORIZED);
			respObj.setMessage("Session experée");

//			Status status = new Status();
//			status.setCode(StatusCode.FUNC_SESSION_EXPIRED);
//			status.setMessage(StatusMessage.FUNC_SESSION_EXPIRED);
//			respObj.setStatus(status);
			responseValue = new Gson().toJson(respObj, Response.class);
			res.setContentType(MediaType.APPLICATION_JSON_VALUE);
		} catch (Exception e) {
			slf4jLogger.warn(e.getMessage());
		}
		return responseValue;
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
