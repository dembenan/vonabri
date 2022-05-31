package ci.palmafrique.vonabri.utils;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ci.palmafrique.vonabri.utils.contract.Response;



@Component
public class SmtpUtils {


	@Autowired
	private ExceptionUtils exceptionUtils;

	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private Environment environment;
	@Autowired
	private SmtpProperties smtpProperties;
	@Autowired
	private ParamsUtils paramsUtils;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(SmtpUtils.class);

	public static List<String> URI_AS_CHECK = Arrays.asList("user/login", "user/forgotPassword",
			"user/forgotPasswordValidation");

	public static String encrypt(String str) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] hashedBytes = digest.digest(str.getBytes("UTF-8"));

		return convertByteArrayToHexString(hashedBytes);
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	public static String generateAlphanumericCode(Integer nbreCaractere) {
		String formatted = null;
		formatted = RandomStringUtils.randomAlphanumeric(nbreCaractere).toLowerCase();
		return formatted;
	}

	@Async
	public <T> Response<T> sendEmail(Map<String, String> from, List<Map<String, String>> toRecipients, String subject,
			String body, List<String> attachmentsFilesAbsolutePaths, Context context, String templateName,
			Locale locale) {
		Response<T> response = new Response<T>();
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		String smtpServer = smtpProperties.getHost();
		if (smtpServer != null) {
			Boolean auth = false;
			javaMailSender.setHost(smtpServer);
			javaMailSender.setPort(smtpProperties.getPort());
			javaMailSender.setUsername(smtpProperties.getLogin());
			javaMailSender.setPassword(smtpProperties.getPassword());
			javaMailSender.setProtocol("smtp");
			// ADD NEW CONFIG
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			// auth = true;
			javaMailSender.setJavaMailProperties(getMailProperties(smtpProperties.getHost(), auth));

			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE, "UTF-8");
				// sender
				helper.setFrom(new InternetAddress(from.get("email"), from.get("user")));
				// sender
				// recipients
				List<InternetAddress> to = new ArrayList<InternetAddress>();
				for (Map<String, String> recipient : toRecipients) {
					to.add(new InternetAddress(recipient.get("email"), recipient.get("user")));
				}
				helper.setTo(to.toArray(new InternetAddress[0]));

				// Subject and body
				helper.setSubject(subject);
				if (context != null && templateName != null) {
					body = templateEngine.process(templateName, context);
				}
				helper.setText(body, true);

				// Attachments
				if (attachmentsFilesAbsolutePaths != null && !attachmentsFilesAbsolutePaths.isEmpty()) {
					for (String attachmentPath : attachmentsFilesAbsolutePaths) {
						File pieceJointe = new File(attachmentPath);
						FileSystemResource file = new FileSystemResource(attachmentPath);
						if (pieceJointe.exists() && pieceJointe.isFile()) {
							helper.addAttachment(file.getFilename(), file);
						}
					}
				}

				javaMailSender.send(message);
				response.setHasError(Boolean.FALSE);
				/// gerer les cas d'exeption de non envoi de mail
			} catch (MessagingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			}
		}
		return response;
	}

	@Async
	public void sendEmailNew() throws AddressException, MessagingException {
		String username = "Siaka Dembele"; 
		String gmail = "dembenan2019@gmail.com";		
		String password = "Chatobrillant85";
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.mailtrap.io");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
		
		Session session = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(username, password);
		    }
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(gmail));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse(gmail));
		message.setSubject("Mail Subject");

		String msg = "This is my first email using JavaMailer";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}
	public  void sendEmailS(Session session, String toEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("dembenan2019@gmail.com", "NoReply-JD"));

	      msg.setReplyTo(InternetAddress.parse("dembenan2019@gmail.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}


	public <T> Response<T> sendEmailWithObj(Map<String, String> from, List<Map<String, Object>> toRecipients, String subject,
			String body, List<String> attachmentsFilesAbsolutePaths, Context context, String templateName,
			Locale locale) {
		Response<T> response = new Response<T>();
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		String smtpServer = smtpProperties.getHost();
		if (smtpServer != null) {
			Boolean auth = false;
			javaMailSender.setHost(smtpServer);
			javaMailSender.setPort(smtpProperties.getPort());
			javaMailSender.setUsername(smtpProperties.getLogin());
			javaMailSender.setPassword(smtpProperties.getPassword());
			
			// ADD NEW CONFIG
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			// auth = true;
			javaMailSender.setJavaMailProperties(getMailProperties(smtpProperties.getHost(), auth));

			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE, "UTF-8");
				// sender
				helper.setFrom(new InternetAddress(from.get("email"), from.get("user")));
				// sender
				// recipients
				List<InternetAddress> to = new ArrayList<InternetAddress>();
				for (Map<String, Object> recipient : toRecipients) {
					to.add(new InternetAddress(""+recipient.get("email"), ""+recipient.get("user")));
				}
				helper.setTo(to.toArray(new InternetAddress[0]));

				// Subject and body
				helper.setSubject(subject);
				if (context != null && templateName != null) {
					body = templateEngine.process(templateName, context);
				}
				helper.setText(body, true);

				// Attachments
				if (attachmentsFilesAbsolutePaths != null && !attachmentsFilesAbsolutePaths.isEmpty()) {
					for (String attachmentPath : attachmentsFilesAbsolutePaths) {
						File pieceJointe = new File(attachmentPath);
						FileSystemResource file = new FileSystemResource(attachmentPath);
						if (pieceJointe.exists() && pieceJointe.isFile()) {
							helper.addAttachment(file.getFilename(), file);
						}
					}
				}

				javaMailSender.send(message);
				response.setHasError(Boolean.FALSE);
				/// gerer les cas d'exeption de non envoi de mail
			} catch (MessagingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			}
		}
		return response;
	}

	
	
	@Async
	public <T> Response<T> sendEmailAsync(Map<String, String> from, List<Map<String, String>> toRecipients,
			String subject, String body, List<String> attachmentsFilesAbsolutePaths, Context context,
			String templateName, Locale locale) {
		Response<T> response = new Response<T>();
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

//		/*
//		 * A retirer
//		 */
//		if(true)
//		return response;
//		
//		/*
//		 * retirer
//		 */

		String smtpServer = smtpProperties.getHost();
		if (smtpServer != null) {
			Boolean auth = false;
			javaMailSender.setHost(smtpServer);
			javaMailSender.setPort(smtpProperties.getPort());
			javaMailSender.setUsername(smtpProperties.getLogin());
			javaMailSender.setPassword(smtpProperties.getPassword());
			auth = true;

			javaMailSender.setJavaMailProperties(getMailProperties(smtpProperties.getHost(), auth));

			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE, "UTF-8");
//				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);

				// sender
				helper.setFrom(new InternetAddress(from.get("email"), from.get("user")));
				// sender

				// recipients
				List<InternetAddress> to = new ArrayList<InternetAddress>();
				for (Map<String, String> recipient : toRecipients) {
					to.add(new InternetAddress(recipient.get("email"), recipient.get("user")));
				}
				helper.setTo(to.toArray(new InternetAddress[0]));

				// Subject and body
				helper.setSubject(subject);
				if (context != null && templateName != null) {
					body = templateEngine.process(templateName, context);
				}
				helper.setText(body, true);

				// Attachments
				if (attachmentsFilesAbsolutePaths != null && !attachmentsFilesAbsolutePaths.isEmpty()) {
					for (String attachmentPath : attachmentsFilesAbsolutePaths) {
						File pieceJointe = new File(attachmentPath);
						FileSystemResource file = new FileSystemResource(attachmentPath);
						if (pieceJointe.exists() && pieceJointe.isFile()) {
							helper.addAttachment(file.getFilename(), file);
						}
					}
				}

				javaMailSender.send(message);
				response.setHasError(Boolean.FALSE);
				/// gerer les cas d'exeption de non envoi de mail
			} catch (MessagingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			}
		}
		return response;
	}

	private Properties getMailProperties(String host, Boolean auth) {
		Properties properties = new Properties();
		auth = true;
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", auth.toString());
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.starttls.required", "true");
		properties.setProperty("mail.debug", "true");
		properties.put("mail.smtp.sendpartial", "true");
		if (host.equals("smtp.gmail.com")) {
			properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
			properties.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		}
		return properties;
	}

	public static boolean isValidEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}


}
