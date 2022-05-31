
/*
 * Created on 2019-05-09 ( Time 15:04:13 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Params Utils
 * 
 * @author SFL Back-End developper
 *
 */
@Data
@Component
public class ParamsUtils { 
	
	//Urls
	
//	@Value("${image.directory}")
//	private String imageDirectory;
//	
//	@Value("${textfile.directory}")
//	private String textfileDirectory;
//	
//	@Value("${video.directory}")
//	private String videoDirectory;
//
//	@Value("${otherfile.directory}")
//	private String otherfileDirectory;
//
//	@Value("${url.root}")
//	private String urlRoot;
//
//	@Value("${path.root}")
//	private String rootFilesPath;
//
//	// Files location
//
//	@Value("${url.image.link}")
//	private String urlImageLink;
//	
//	@Value("${parametre.dossier}")
//	private String pathDossier;
////
////




//	// SERVEUR DE MAIL

//
////// api d'envoi de SMS avec ORANGE
////
//	@Value("${api.sms.contentType.value}")
//	private String smsContentTypeValue;
//
//	@Value("${api.sms.contentType.key}")
//	private String smsContentTypeKey;
//
//	@Value("${api.sms.header.authorization}")
//	private String smsHeaderAuthorization;
//
//	@Value("${api.sms.bearer}")
//	private String smsBearer;
//
//	@Value("${api.sms.url}")
//	private String smsUrl;
////
//	@Value("${api.sms.senderAddress}")
//	private String smsSenderAddress;
////
//	
//	private String smsToken;
//
//	@Value("${api.sms.auto.header}")
//	private String smsAutoHeader;
//	
//	
//	// api d'envoi de SMS avec infobip
//	@Value("${api2.sms.contentType.value}")
//	private String sms2ContentTypeValue;
//
//	@Value("${api2.sms.contentType.key}")
//	private String sms2ContentTypeKey;
//
//	@Value("${api2.sms.header.authorization}")
//	private String sms2HeaderAuthorization;
//
//	@Value("${apiKeyPrefix}")
//	private String apiKeyPrefix;
//
//	@Value("${apiKey}")
//	private String apiKey;
//
//	@Value("${basePath}")
//	private String basePath;

	
	@Value("${url.admin}")
	private String urlAdmin;
	
	
//	 les parametres de SMTP
	@Value("${smtp.mail.port}")
	private Integer smtpPort;
	@Value("${smtp.mail.login}")
	private String smtpLogin;
	@Value("${smtp.mail.password}")
	private String smtpPassword;
	@Value("${smtp.mail.host}")
	private String smtpHost;

	// parametres redis

	private static String redisServerHost;

	@Value("${redis.server.host}")
	public void setRedisServerHost(String info) {
		redisServerHost = info;
	}

	public static String getRedisServerHost() {
		return redisServerHost;
	}

	private static int redisServerPort;

	@Value("${redis.server.port}")
	public void setRedisServerPort(int info) {
		redisServerPort = info;
	}

	public static int getRedisServerPort() {
		return redisServerPort;
	}

//	@Value("${spring.datasource.driverClassName}")
//	 private String userDataSource;
//	 
//	 @Value("${spring.datasource.url}")
//	 private String urlDb;
//	 
//	 @Value("${spring.datasource.username}")
//	 private String userDb;
//
//	 @Value("${spring.datasource.password}")
//	 private String passDb;
	
//	 @Value("${url.api.location}")
//	private String getLocationByLngLat;
	 
}