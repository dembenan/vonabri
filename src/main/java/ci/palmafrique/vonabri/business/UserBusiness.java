



                                                                    															

/*
 * Java transformer for entity table user 
 * Created on 2022-04-25 ( Time 03:17:05 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import ci.palmafrique.vonabri.dao.entity.Fonctionnalite;
import ci.palmafrique.vonabri.dao.entity.Profil;
import ci.palmafrique.vonabri.dao.entity.Travailleur;
import ci.palmafrique.vonabri.dao.entity.User;
import ci.palmafrique.vonabri.dao.entity.UserType;
import ci.palmafrique.vonabri.dao.repository.FonctionnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.ProfilFonctionnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.ProfilRepository;
import ci.palmafrique.vonabri.dao.repository.TravailleurRepository;
import ci.palmafrique.vonabri.dao.repository.UserRepository;
import ci.palmafrique.vonabri.dao.repository.UserTypeRepository;
import ci.palmafrique.vonabri.jwt.JwtTokenUtil;
import ci.palmafrique.vonabri.jwt.SecurityConstants;
import ci.palmafrique.vonabri.utils.CloudinaryService;
import ci.palmafrique.vonabri.utils.ExceptionUtils;
import ci.palmafrique.vonabri.utils.FunctionalError;
import ci.palmafrique.vonabri.utils.ParamsUtils;
import ci.palmafrique.vonabri.utils.RedisUser;
import ci.palmafrique.vonabri.utils.SmtpUtils;
import ci.palmafrique.vonabri.utils.TechnicalError;
import ci.palmafrique.vonabri.utils.Utilities;
import ci.palmafrique.vonabri.utils.Validate;
import ci.palmafrique.vonabri.utils.contract.IBasicBusiness;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.utils.dto.FonctionnaliteDto;
import ci.palmafrique.vonabri.utils.dto.UserDto;
import ci.palmafrique.vonabri.utils.dto.transformer.FonctionnaliteTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.UserTransformer;
import ci.palmafrique.vonabri.utils.enums.FunctionalityEnum;
import lombok.extern.java.Log;

/**
BUSINESS for table "user"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class UserBusiness implements IBasicBusiness<Request<UserDto>, Response<UserDto>> {

	private Response<UserDto> response;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private TravailleurRepository travailleurRepository;
	@Autowired
	private ProfilRepository profilRepository;
	@Autowired
	private ProfilFonctionnaliteRepository profilFonctionnaliteRepository;

	@Autowired
	private FonctionnaliteRepository fonctionnaliteRepository;
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private ParamsUtils paramsUtils;
	@Autowired
	private SmtpUtils smtpUtils;
	
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private RedisUser redisUser;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private HttpServletRequest requestBasic;
	
	
	private Context context;
	private final String ENTETE = "Vonabri";
	private final String TITRE = "Administrator";
	
	
	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public UserBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<UserDto> create(Request<UserDto> request, Locale locale)  {
		log.info("----begin create User-----");
		
		response = new Response<UserDto>();

		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			List<User> items = new ArrayList<User>();
			List<UserDto> itemEmails = new ArrayList<UserDto>();
			for (UserDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				//fieldsToVerify.put("email", dto.getEmail());
				//fieldsToVerify.put("password", dto.getPassword());
				fieldsToVerify.put("profilId", dto.getProfilId());
				fieldsToVerify.put("userTypeId", dto.getUserTypeId());
				//fieldsToVerify.put("travailleurId", dto.getTravailleurId());

//				fieldsToVerify.put("isLocked", dto.getIsLocked());
//				fieldsToVerify.put("isConnected", dto.getIsConnected());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if user to insert do not exist
				User existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("user id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// verif unique email in db
				existingEntity = userRepository.findByEmail(dto.getEmail(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("user email -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique email in items to save
				if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
					response.setHasError(true);
					return response;
				}

				// Verify if userType exist
				UserType existingUserType = null;
				if (dto.getUserTypeId() != null && dto.getUserTypeId() > 0){
					existingUserType = userTypeRepository.findOne(dto.getUserTypeId(), false);
					if (existingUserType == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("userType userTypeId -> " + dto.getUserTypeId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if profil exist
				Profil existingProfil = null;
				if (dto.getProfilId() != null && dto.getProfilId() > 0){
					existingProfil = profilRepository.findOne(dto.getProfilId(), false);
					if (existingProfil == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("profil profilId -> " + dto.getProfilId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if travailleur exist
				Travailleur existingTravailleur = null;
				if (dto.getTravailleurId() != null && dto.getTravailleurId() > 0){
					existingTravailleur = travailleurRepository.findOne(dto.getTravailleurId(), false);
					if (existingTravailleur == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("travailleur travailleurId -> " + dto.getTravailleurId(), locale));
						response.setHasError(true);
						return response;
					}
					User us = userRepository.findByTravailleurId(existingTravailleur.getId(),false);
					if (us != null) {
						response.setStatus(functionalError.DATA_EXIST("User avec -> " + existingTravailleur.getEmail(), locale));
						response.setHasError(true);
						return response;
					}
					dto.setEmail(existingTravailleur.getEmail());
					
				}else {
					fieldsToVerify.put("email", dto.getEmail());
					if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
						response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
						response.setHasError(true);
						return response;
					}
				}
				
				User entityToSave = null;
				entityToSave = UserTransformer.INSTANCE.toEntity(dto, existingUserType, existingProfil,existingTravailleur);
				String password = Utilities.generateAlphabeticCode(8);
				UserDto mailDto = new UserDto();
				mailDto.setEmail(dto.getEmail());
				mailDto.setPassword(password);
				itemEmails.add(mailDto);
				
				entityToSave.setPassword(Utilities.encrypt(password));
				if (dto.getIsSuperAdmin() != null) {
					entityToSave.setIsSuperAdmin(dto.getIsSuperAdmin());
				}
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}
			if (!items.isEmpty()) {
				List<User> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = userRepository.saveAll((Iterable<User>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("user", locale));
					response.setHasError(true);
					return response;
				}
				itemEmails.stream().forEach(mailDto -> {
					if (Utilities.notBlank(mailDto.getEmail())) {
						// set mail to user
						Map<String, String> from = new HashMap<>();
						from.put("email", paramsUtils.getSmtpLogin());
						from.put("user", ENTETE);
						// recipients
						
//						List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
//						items.stream().forEach(user -> {
//							Map<String, String> recipient = new HashMap<String, String>();
//							recipient = new HashMap<String, String>();
//							recipient.put("email", dto.getEmail());
//							// recipient.put("user", user.getLogin());
//							toRecipients.add(recipient);
//						});
						List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
						Map<String, String> recipient = new HashMap<String, String>();
						recipient = new HashMap<String, String>();
						recipient.put("email", mailDto.getEmail());
						// recipient.put("user", user.getLogin());
						toRecipients.add(recipient);
						// choisir la vraie url
						String appLink = paramsUtils.getUrlAdmin();
						// subject
						String subject = "Vonabri access";
						context = new Context();
						String template = "mail_new_mdp";
						context.setVariable("email", mailDto.getEmail());
						context.setVariable("entete", ENTETE);
						context.setVariable("appLink", appLink);
						context.setVariable("password", mailDto.getPassword());
						context.setVariable("date", dateTimeFormat.format(new Date()));
						log.info("********************* from " + from);
						log.info("********************* Recipeints " + toRecipients);
						log.info("********************* subject " + toRecipients);
						log.info("********************* context " + context);
						smtpUtils.sendEmail(from, toRecipients, subject, null, null, context, template, null);
					}
				});				
				List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UserTransformer.INSTANCE.toLiteDtos(itemsSaved) : UserTransformer.INSTANCE.toDtos(itemsSaved);
				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {	
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setHasError(false);
			}

			log.info("----end create User-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> resetPassword(Request<UserDto> request, Locale locale)  {
		log.info("----begin resetPassword User-----");
		
		response = new Response<UserDto>();

		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			Response<UserDto> userResponse = isGranted(request, FunctionalityEnum.RESET_PASSWORD_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<User> items = new ArrayList<User>();
			List<UserDto> itemsSend = new ArrayList<UserDto>();

			for (UserDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId() );
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}
				// Verify if user to insert do not exist
				User existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("user id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// verif unique email in db
				existingEntity = userRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("user  -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}
				String password = Utilities.generateAlphabeticCode(8);
				UserDto userSend = new UserDto();
				userSend.setEmail(existingEntity.getEmail());
				userSend.setPassword(password);
				itemsSend.add(userSend);
				log.info("********************* password GENERATE " + password);

				existingEntity.setPassword(Utilities.encrypt(password));
				existingEntity.setUpdatedAt(Utilities.getCurrentDate());
				existingEntity.setUpdatedBy(request.getUser());
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				List<User> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = userRepository.saveAll((Iterable<User>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("user", locale));
					response.setHasError(true);
					return response;
				}

				
				if (!itemsSend.isEmpty()) {
					
					for (UserDto dto : itemsSend) {
						
						log.info("********************* password GENERATE AFTER AVED" + dto.getPassword());

						if (Utilities.notBlank(dto.getEmail())) {
							// set mail to user
							Map<String, String> from = new HashMap<>();
							from.put("email", paramsUtils.getSmtpLogin());
							from.put("user", ENTETE);
							// recipients
							List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
							Map<String, String> recipient = new HashMap<String, String>();
							recipient = new HashMap<String, String>();
							recipient.put("email", dto.getEmail());
							// recipient.put("user", user.getLogin());
							toRecipients.add(recipient);
							
//							items.stream().forEach(user -> {
//								Map<String, String> recipient = new HashMap<String, String>();
//								recipient = new HashMap<String, String>();
//								recipient.put("email", dto.getEmail());
//								// recipient.put("user", user.getLogin());
//								toRecipients.add(recipient);
//							});
							// choisir la vraie url
							String appLink = paramsUtils.getUrlAdmin();

							// subject
							String subject = "Vonabri reset password";
							String contenu = "Your default credencial to Vonabri dashboad is <br/><br/>";
							contenu += "EMAIL : " + dto.getEmail();
							contenu += "<br/><br/>PASSWORD : " + dto.getPassword();

							String body = "";
							context = new Context();
							// subject
							context = new Context();
							String template = "mail_new_mdp";
							context.setVariable("email", dto.getEmail());
							context.setVariable("entete", ENTETE);
							context.setVariable("appLink", appLink);
							context.setVariable("password", dto.getPassword());
							context.setVariable("date", dateTimeFormat.format(new Date()));
							log.info("********************* from " + from);
							log.info("********************* Recipeints " + toRecipients);
							log.info("********************* subject " + toRecipients);
							log.info("********************* context " + context);
							smtpUtils.sendEmail(from, toRecipients, subject, null, null, context, template, null);

						}

					}
				}

				List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UserTransformer.INSTANCE.toLiteDtos(itemsSaved) : UserTransformer.INSTANCE.toDtos(itemsSaved);
				
				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {	
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setHasError(false);
			}

			log.info("----end resetPassword User-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * update User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<UserDto> update(Request<UserDto> request, Locale locale)  {
		log.info("----begin update User-----");
		
		response = new Response<UserDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			Response<UserDto> userResponse = isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<User> items = new ArrayList<User>();
			
			for (UserDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la user existe
				User entityToSave = null;
				entityToSave = userRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("user id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if userType exist
				if (dto.getUserTypeId() != null && dto.getUserTypeId() > 0){
					UserType existingUserType = userTypeRepository.findOne(dto.getUserTypeId(), false);
					if (existingUserType == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("userType userTypeId -> " + dto.getUserTypeId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setUserType(existingUserType);
				}
				// Verify if userType exist
				if (dto.getTravailleurId() != null && dto.getTravailleurId() > 0){
					Travailleur existingTravailleur = travailleurRepository.findOne(dto.getTravailleurId(), false);
					if (existingTravailleur == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("Travailleur TravailleurId -> " + dto.getTravailleurId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setTravailleur(existingTravailleur);
				}
				// Verify if profil exist
				if (dto.getProfilId() != null && dto.getProfilId() > 0){
					Profil existingProfil = profilRepository.findOne(dto.getProfilId(), false);
					if (existingProfil == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("profil profilId -> " + dto.getProfilId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setProfil(existingProfil);
				}
				if (Utilities.notBlank(dto.getEmail())) {
					entityToSave.setEmail(dto.getEmail());
				}
				if (Utilities.notBlank(dto.getPassword())) {
					entityToSave.setPassword(dto.getPassword());
				}
				if (dto.getIsLocked() != null) {
					entityToSave.setIsLocked(dto.getIsLocked());
				}
				if (Utilities.notBlank(dto.getDeletedAt())) {
					entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
				}
				if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
					entityToSave.setCreatedBy(dto.getCreatedBy());
				}
				if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
					entityToSave.setUpdatedBy(dto.getUpdatedBy());
				}
				if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
					entityToSave.setDeletedBy(dto.getDeletedBy());
				}
				if (dto.getIsConnected() != null) {
					entityToSave.setIsConnected(dto.getIsConnected());
				}
				entityToSave.setUpdatedAt(Utilities.getCurrentDate());
				entityToSave.setUpdatedBy(request.getUser());
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<User> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = userRepository.saveAll((Iterable<User>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("user", locale));
					response.setHasError(true);
					return response;
				}
				List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UserTransformer.INSTANCE.toLiteDtos(itemsSaved) : UserTransformer.INSTANCE.toDtos(itemsSaved);

				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setHasError(false);
			}

			log.info("----end update User-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * delete User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<UserDto> delete(Request<UserDto> request, Locale locale)  {
		log.info("----begin delete User-----");
		
		response = new Response<UserDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			Response<UserDto> userResponse = isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<User> items = new ArrayList<User>();
			
			for (UserDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la user existe
				User existingEntity = null;
				existingEntity = userRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------



				existingEntity.setDeletedAt(Utilities.getCurrentDate());
				existingEntity.setDeletedBy(request.getUser());
				existingEntity.setIsDeleted(true);
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				// supprimer les donnees en base
				userRepository.saveAll((Iterable<User>) items);

				response.setHasError(false);
			}

			log.info("----end delete User-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * get User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<UserDto> getByCriteria(Request<UserDto> request, Locale locale) {
		log.info("----begin get User-----");
		
		response = new Response<UserDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<User> items = null;
			items = userRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UserTransformer.INSTANCE.toLiteDtos(items) : UserTransformer.INSTANCE.toDtos(items);
				final int size = items.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setCount(userRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("user", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get User-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	
	public Response<UserDto> login(Request<UserDto> request,String accessToken, Locale locale) {
		log.info("----begin login-----");

		response = new Response<UserDto>();
		try {
			UserDto data = request.getData();
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("email", data.getEmail());
			fieldsToVerify.put("password", data.getPassword());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
				
				data.setEmail(data.getEmail().trim());
				User item = userRepository.findByEmailAndPassword(data.getEmail(),Utilities.encrypt(data.getPassword()), false);
				if (item == null) {
					response.setStatus(functionalError.LOGIN_FAIL(locale));
					response.setHasError(true);
					return response;
				}
				if (item.getTravailleur() != null && !item.getTravailleur().getStatut().getCode().equals("CDI") && item.getTravailleur().getDateFinContrat().before(Utilities.getCurrentDate())) {
					response.setStatus(functionalError.AUTH_FAIL("Compte verrouillé. La date de fin de votre contrat : "+item.getTravailleur().getStatut().getLibelle()+" est arrivée à echéance. si vous pensez que ce message n'est pas correcte Contacter un administrateur !", locale));
					response.setHasError(Boolean.TRUE);
					return response;
				}
//				if (item.getIsConnected() != null && item.getIsConnected()) {
//					response.setStatus(functionalError.USER_ALREADY_CONNECTED("---->"+data.getEmail(),locale));
//					response.setHasError(true);
//					return response;
//				}

//				if (Utilities.isTrue(item.getIsLocked())) {
//					response.setStatus(functionalError.USER_IS_LOCKED(String.format("%1$s est vérouillé(e)", item.getEmail()), locale));
//					response.setHasError(true);
//					return response;
//				}
				if (item.getIsLocked() != null && item.getIsLocked().equals(Boolean.TRUE)) {
					response.setStatus(functionalError.AUTH_FAIL("Compte verrouillé. Contacter un administrateur !", locale));
					response.setHasError(Boolean.TRUE);
					return response;
				}

				// List<String> fonctionnalites = new ArrayList<String>();
				List<Fonctionnalite> list = new ArrayList<Fonctionnalite>();
				List<FonctionnaliteDto> listDto = new ArrayList<FonctionnaliteDto>();
				if (item.getProfil() != null && item.getProfil().getId() > 0) {
					list = profilFonctionnaliteRepository.findFonctionnaliteByProfilId(item.getProfil().getId(), false);
					if (Utilities.isNotEmpty(list)) {
						for (FonctionnaliteDto fdto : FonctionnaliteTransformer.INSTANCE.toDtos(list)) {
							fdto.setCreatedAt(null);
							fdto.setCreatedBy(null);
							fdto.setIsDeleted(null);
							listDto.add(fdto);
						}
					}
				}
				item.setIsConnected(Boolean.TRUE);
				User userSaved = userRepository.save(item);
				if(userSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("User", locale));
					response.setHasError(Boolean.TRUE);
					return response;
				}
				System.out.println("TRAVAILLEUR =====>"+userSaved.getTravailleur());
				UserDto itemsDto = UserTransformer.INSTANCE.toDto(userSaved);
				if (userSaved.getTravailleur() != null && userSaved.getTravailleur().getPhoto() != null) {
					String photo = cloudinaryService.getPhotoUrl(userSaved.getTravailleur().getPhoto());

					if (Utilities.notBlank(photo)) {
						itemsDto.setPhoto(photo);
					}
				}
				// String token =
				// String.valueOf(userSaved.getId()).concat("_VONABRI_").concat(Utilities.generateCodeOld());
				// String tokenEncrypted = Utilities.encryptWalletKeyString(token);
				redisUser.saveValueWithExpirationMinutes(accessToken, itemsDto, 60);
				itemsDto.setToken(accessToken);
				itemsDto.setDatasFonctionnalites(listDto);
				response.setItem(itemsDto);
				response.setHasError(false);
				response.setStatus(functionalError.SUCCESS("Connexion reussie", locale));
			
				
			log.info("----end login-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	public Response<UserDto> logout(Request<UserDto> request, Locale locale) {
		log.info("----begin logout-----");

		response = new Response<UserDto>();
		try {
			
			String token = getTokenFromRequestBasic();
			redisUser.delete(token);
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("Deconnexion reussie", locale));

			log.info("----end logout-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	// cron a executer chaque jour a 8h
//	@Scheduled(cron = "0 0 8 * * *")
    //@Scheduled(cron = "*/10 * * * * *")
	//cron a executer chaque 1er de mois a 00h
    @Scheduled(cron = "0 0 0 L * *")
	public void cronToResetAllPassword() throws ParseException {
		log.info("----begin cron reset password user-----");

		List<User> users = userRepository.findByIsDeleted(false);
		
		users.parallelStream().forEach(user -> {
			String newPassword = Utilities.generateAlphabeticCode(8);
			try {

					// set mail to user
					Map<String, String> from = new HashMap<>();
					from.put("email", paramsUtils.getSmtpLogin());
					from.put("user", ENTETE);
					// recipients
					List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
					Map<String, String> recipient = new HashMap<String, String>();
					recipient = new HashMap<String, String>();
					recipient.put("email", user.getEmail());
					toRecipients.add(recipient);

					// subject
					String subject = "Vonabri access";
					context = new Context();
					String template = "mail_new_mdp";
					context.setVariable("email", user.getEmail());
					context.setVariable("entete", ENTETE);
					context.setVariable("password", newPassword);
					context.setVariable("date", dateTimeFormat.format(new Date()));
					log.info("********************* from " + from);
					log.info("********************* Recipeints " + toRecipients);
					log.info("********************* subject " + toRecipients);
					log.info("********************* context " + context);
					
					Response<UserDto> response = smtpUtils.sendEmail(from, toRecipients, subject, null, null, context, template, null);
					log.info("********************* send mail response " + response.isHasError());
					if(Utilities.isFalse(response.isHasError()) ) {
						user.setPassword(Utilities.encrypt(newPassword));
						User itemsSaved = userRepository.save((user));
						if (itemsSaved == null) {
							System.out.println("save fail itemsSaved for ====>"+user.getEmail());
						}
					}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		});

	}
	/**
	 * get full UserDto by using User as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private UserDto getFullInfos(UserDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		if(dto.getTravailleurId() != null && dto.getTravailleurId() > 0) {
			Travailleur tr = travailleurRepository.findOne(dto.getTravailleurId(), false);
	        if (tr.getPhoto() != null) {
	            String photo = cloudinaryService.getPhotoUrl(tr.getPhoto());
				if (Utilities.notBlank(photo)) {
					dto.setPhoto(photo);
				}
	            
	        }
		}
		// put code here
		dto.setPassword(null);
		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	/**
	 * 
	 * @param userId
	 * @param functionalityCode
	 * @param locale
	 * @return
	 */

	public String getTokenFromRequestBasic() {

		String tokenFromHeader = requestBasic.getHeader(SecurityConstants.HEADER_STRING);
		String token = tokenFromHeader.substring(7, tokenFromHeader.length());

		return token;
	}
	
	public  String getEmailByToken(String tokenFromHeader) {
		
		String email = null;
 			//String token = tokenFromHeader.substring(7, tokenFromHeader.length());
 			email = jwtTokenUtil.getUsernameFromToken(tokenFromHeader);
 			System.out.println("===============================>USERNAME PHONE"+email+"================================>");
// 			Client currentClientEntity = clientRepository.findByPhoneNumber(phoneClient, false);
// 			System.out.println("CLIENT FROM JWT PHONE NUMBER"+currentClientEntity);

 			System.out.println("jwtTokenjwtToken=========================>"+tokenFromHeader);

		return email;
	}
	public void getUserAgent() {

	    String browserType = requestBasic.getHeader("User-Agent"); // This is the line you're after
		System.out.println("browserType  device  browserType======>"+browserType);

	    // This will just output the user agent to the browser
//	    response.getOutputStream().write(browserType.getBytes());
//	    response.getOutputStream().close();
	}

    @SuppressWarnings("rawtypes")
	public Response<UserDto> isGranted(Request request, String functionalityCode, Locale locale){
		log.info("----begin get isGranted-----");

		response = new Response<UserDto>();
		try {
	 		String token = getTokenFromRequestBasic();
			UserDto getRedisSaved = redisUser.get(token);
			System.out.println("getRedisSaved======>"+getRedisSaved);
			if (getRedisSaved == null) {
				response.setStatus(functionalError.USER_SESSION_EXPIRED("Votre session a expiré en raison d'une inactivité de : 5 min." , locale));
				response.setHasError(true);
				return response;
 			}
			getUserAgent();
			redisUser.getExpiration(token);
				User currentUser = userRepository.findOne(getRedisSaved.getId(), false);
				if (currentUser == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("Utilisateur -> " + getRedisSaved.getId(), locale));
					response.setHasError(true);
					return response;
				}
				if (Utilities.isTrue(currentUser.getIsLocked())) {
					response.setStatus(functionalError.REQUEST_FAIL("L'utilisateur "+currentUser.getEmail()+" est verouille(e)" , locale));
					response.setHasError(true);
					return response;
	 			}
				if (Utilities.isFalse(currentUser.getIsConnected())) {
					response.setStatus(functionalError
							.DISALLOWED_OPERATION("L'utilisateur " + currentUser.getEmail() + " n'est pas connecte", locale));
					response.setHasError(true);
					return response;
				}
				if (currentUser.getProfil() == null) {
					response.setStatus(functionalError.DISALLOWED_OPERATION(
							"L'utilisateur " + currentUser.getEmail() + " n'a pas le droit de se connecter au dashboard",
							locale));
					response.setHasError(true);
					return response;
				}
//				if (Utilities.isFalse(currentUser.getIsSuperAdmin())) {
//					if (Utilities.notBlank(functionalityCode)) {
//						Fonctionnalite functionality = fonctionnaliteRepository.findByCode(functionalityCode ,false);
//						if (functionality == null) {
//							response.setHasError(true);
//							//response.setStatus(functionalError.USER_NOT_GRANTED("", locale));
//							response.setStatus(functionalError.DISALLOWED_OPERATION("pour "+ currentUser.getEmail(), locale));
//							return response;
//						}
//						//ProfilFonctionnalite profilFunctionality = profilFonctionnaliteRepository.isGranted(currentUser.getProfil().getId(), functionalityCode ,false);
//						ProfilFonctionnalite profilFunctionality = profilFonctionnaliteRepository.findByProfilIdAndFonctionnaliteId(currentUser.getProfil().getId(), functionality.getId() ,false);
	//
//						if (profilFunctionality == null) {
//							response.setHasError(true);
//							response.setStatus(functionalError.DISALLOWED_OPERATION("pour "+ currentUser.getEmail(), locale));
//							return response;
//						}
//					}
//				}
				redisUser.setExpiration(token,60);
				redisUser.getExpiration(token);

				request.setUser(currentUser.getId());
				response.setHasError(false);
				log.info("----end get isGranted-----");

			


		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}

		return response;
	}

  }
