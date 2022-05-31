



                                                                    															

/*
 * Java transformer for entity table user 
 * Created on 2022-04-25 ( Time 03:17:05 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.business;

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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import ci.palmafrique.vonabri.dao.entity.Fonctionnalite;
import ci.palmafrique.vonabri.dao.entity.Profil;
import ci.palmafrique.vonabri.dao.entity.User;
import ci.palmafrique.vonabri.dao.entity.UserType;
import ci.palmafrique.vonabri.dao.repository.FonctionnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.ProfilFonctionnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.ProfilRepository;
import ci.palmafrique.vonabri.dao.repository.UserRepository;
import ci.palmafrique.vonabri.dao.repository.UserTypeRepository;
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
	private ProfilRepository profilRepository;
	@Autowired
	private ProfilFonctionnaliteRepository profilFonctionnaliteRepository;

	@Autowired
	private FonctionnaliteRepository fonctionnaliteRepository;

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
		String password = null;

		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("token", request.getToken());
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
				fieldsToVerify.put("email", dto.getEmail());
				//fieldsToVerify.put("password", dto.getPassword());
				fieldsToVerify.put("profilId", dto.getProfilId());
				fieldsToVerify.put("userTypeId", dto.getUserTypeId());
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
				User entityToSave = null;
				entityToSave = UserTransformer.INSTANCE.toEntity(dto, existingUserType, existingProfil);
				password = Utilities.generateAlphabeticCode(8);

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
				
				for (User dto : itemsSaved) {
					if (Utilities.notBlank(dto.getEmail())) {
						// set mail to user
						Map<String, String> from = new HashMap<>();
						from.put("email", paramsUtils.getSmtpLogin());
						from.put("user", ENTETE);
						// recipients
						List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
						items.stream().forEach(user -> {
							Map<String, String> recipient = new HashMap<String, String>();
							recipient = new HashMap<String, String>();
							recipient.put("email", user.getEmail());
							// recipient.put("user", user.getLogin());
							toRecipients.add(recipient);
						});
						// choisir la vraie url
						String appLink = paramsUtils.getUrlAdmin();

						// subject
						String subject = "Vonabri access";
						String contenu = "Your default credencial to Vonabri dashboad is <br/><br/>";
						contenu += "EMAIL : " + dto.getEmail();
						contenu += "<br/><br/>PASSWORD : " + password;

						String body = "";
						context = new Context();
						// subject
						context = new Context();
						String template = "mail_new_mdp";
						context.setVariable("email", dto.getEmail());
						context.setVariable("entete", ENTETE);
						context.setVariable("appLink", appLink);
						context.setVariable("password", password);
						context.setVariable("date", dateTimeFormat.format(new Date()));
						log.info("********************* from " + from);
						log.info("********************* Recipeints " + toRecipients);
						log.info("********************* subject " + toRecipients);
						log.info("********************* context " + context);
						smtpUtils.sendEmail(from, toRecipients, subject, null, null, context, template, null);

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
			fieldsToVerifyUser.put("token", request.getToken());
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
			fieldsToVerifyUser.put("token", request.getToken());
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
			fieldsToVerifyUser.put("token", request.getToken());
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
//			Map<String, String> from = new HashMap<>();
//			from.put("email", paramsUtils.getSmtpLogin());
//			from.put("user", ENTETE);
//			// recipients
//			String mail = "dembenan2019@gmail.com";
//			String password = "password";
//			List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
//				Map<String, String> recipient = new HashMap<String, String>();
//				recipient = new HashMap<String, String>();
//				recipient.put("email", mail);
//				// recipient.put("user", user.getLogin());
//				toRecipients.add(recipient);
//			// choisir la vraie url
//			String appLink = paramsUtils.getUrlAdmin();
//
//			// subject
//
//
//			String subject = "Vonabri access";
//			String contenu = "Your default credencial to Vonabri dashboad is <br/><br/>";
//			contenu += "EMAIL : " + mail;
//			contenu += "<br/><br/>PASSWORD : " + password;
//
//			String body = "";
//			context = new Context();
//			// subject
//			context = new Context();
//			String template = "mail_new_mdp";
//			context.setVariable("email", mail);
//			context.setVariable("entete", ENTETE);
//			context.setVariable("appLink", appLink);
//			context.setVariable("password", password);
//			context.setVariable("date", dateTimeFormat.format(new Date()));
//			log.info("********************* from " + from);
//			log.info("********************* Recipeints " + toRecipients);
//			log.info("********************* subject " + toRecipients);
//			log.info("********************* context " + context);
//			smtpUtils.sendEmail(from, toRecipients, subject, null, null, context, template, null);

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

	
	public Response<UserDto> login(Request<UserDto> request, Locale locale) {
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
//			if (item.getIsConnected() != null && item.getIsConnected()) {
//				response.setStatus(functionalError.USER_ALREADY_CONNECTED("---->"+data.getEmail(),locale));
//				response.setHasError(true);
//				return response;
//			}

//			if (Utilities.isTrue(item.getIsLocked())) {
//				response.setStatus(functionalError.USER_IS_LOCKED(String.format("%1$s est vérouillé(e)", item.getEmail()), locale));
//				response.setHasError(true);
//				return response;
//			}
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
				UserDto itemsDto = UserTransformer.INSTANCE.toDto(userSaved);
				String token = String.valueOf(userSaved.getId()).concat("_VONABRI_").concat(Utilities.generateCodeOld());
				itemsDto.setToken(token);
				redisUser.saveValueWithExpirationMinutes(token, itemsDto,5);
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
			UserDto data = request.getData();
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("email", data.getEmail());
			//fieldsToVerify.put("password", data.getPassword());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			data.setEmail(data.getEmail().trim());
			User item = userRepository.findByEmail(data.getEmail(), false);
			if (item == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("username -->"+data.getEmail(),locale));
				response.setHasError(true);
				return response;
			}

			item.setIsConnected(Boolean.FALSE);
			userRepository.save(item);
				//UserDto itemsDto = UserTransformer.INSTANCE.toDto(item);
				//itemsDto.setDatasFonctionnalites(listDto);
				//response.setItem(itemsDto);
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
		// put code here

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
    @SuppressWarnings("rawtypes")
	public Response<UserDto> isGranted(Request request, String functionalityCode, Locale locale){
		log.info("----begin get isGranted-----");

		response = new Response<UserDto>();

		try {

			UserDto getRedisSaved = redisUser.get(request.getToken());
			System.out.println("getRedisSaved======>"+getRedisSaved);
			if (getRedisSaved == null) {
				response.setStatus(functionalError.USER_SESSION_EXPIRED("Votre session a expirée : Veillez vous réconnectez" , locale));
				response.setHasError(true);
				return response;
 			}
			
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
//			if (Utilities.isFalse(currentUser.getIsSuperAdmin())) {
//				if (Utilities.notBlank(functionalityCode)) {
//					Fonctionnalite functionality = fonctionnaliteRepository.findByCode(functionalityCode ,false);
//					if (functionality == null) {
//						response.setHasError(true);
//						//response.setStatus(functionalError.USER_NOT_GRANTED("", locale));
//						response.setStatus(functionalError.DISALLOWED_OPERATION("pour "+ currentUser.getEmail(), locale));
//						return response;
//					}
//					//ProfilFonctionnalite profilFunctionality = profilFonctionnaliteRepository.isGranted(currentUser.getProfil().getId(), functionalityCode ,false);
//					ProfilFonctionnalite profilFunctionality = profilFonctionnaliteRepository.findByProfilIdAndFonctionnaliteId(currentUser.getProfil().getId(), functionality.getId() ,false);
//
//					if (profilFunctionality == null) {
//						response.setHasError(true);
//						response.setStatus(functionalError.DISALLOWED_OPERATION("pour "+ currentUser.getEmail(), locale));
//						return response;
//					}
//				}
//			}
			redisUser.saveValueWithExpirationMinutes(request.getToken(), getRedisSaved,5);

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
