



                                                    											

/*
 * Java transformer for entity table anciennete_type 
 * Created on 2022-06-11 ( Time 19:24:46 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ci.palmafrique.vonabri.utils.*;
import ci.palmafrique.vonabri.utils.dto.*;
import ci.palmafrique.vonabri.utils.enums.*;
import ci.palmafrique.vonabri.utils.contract.*;
import ci.palmafrique.vonabri.utils.contract.IBasicBusiness;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.utils.dto.transformer.*;
import ci.palmafrique.vonabri.dao.entity.AncienneteType;
import ci.palmafrique.vonabri.dao.entity.*;
import ci.palmafrique.vonabri.dao.repository.*;

/**
BUSINESS for table "anciennete_type"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class AncienneteTypeBusiness implements IBasicBusiness<Request<AncienneteTypeDto>, Response<AncienneteTypeDto>> {

	private Response<AncienneteTypeDto> response;
	@Autowired
	private AncienneteTypeRepository ancienneteTypeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AncienneteRepository ancienneteRepository;

    @Autowired
    private UserBusiness userBusiness;

	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public AncienneteTypeBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create AncienneteType by using AncienneteTypeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AncienneteTypeDto> create(Request<AncienneteTypeDto> request, Locale locale)  {
		log.info("----begin create AncienneteType-----");
		
		response = new Response<AncienneteTypeDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_ANCIENNETE_TYPE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<AncienneteType> items = new ArrayList<AncienneteType>();
			
			for (AncienneteTypeDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("code", dto.getCode());
				fieldsToVerify.put("libelle", dto.getLibelle());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if ancienneteType to insert do not exist
				AncienneteType existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("ancienneteType id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// verif unique code in db
				existingEntity = ancienneteTypeRepository.findByCode(dto.getCode(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("ancienneteType code -> " + dto.getCode(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique code in items to save
				if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
					response.setHasError(true);
					return response;
				}

				// verif unique libelle in db
				existingEntity = ancienneteTypeRepository.findByLibelle(dto.getLibelle(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("ancienneteType libelle -> " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique libelle in items to save
				if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
					response.setHasError(true);
					return response;
				}

				AncienneteType entityToSave = null;
				entityToSave = AncienneteTypeTransformer.INSTANCE.toEntity(dto);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<AncienneteType> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = ancienneteTypeRepository.saveAll((Iterable<AncienneteType>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("ancienneteType", locale));
					response.setHasError(true);
					return response;
				}
				List<AncienneteTypeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AncienneteTypeTransformer.INSTANCE.toLiteDtos(itemsSaved) : AncienneteTypeTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create AncienneteType-----");
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
	 * update AncienneteType by using AncienneteTypeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AncienneteTypeDto> update(Request<AncienneteTypeDto> request, Locale locale)  {
		log.info("----begin update AncienneteType-----");
		
		response = new Response<AncienneteTypeDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.UPDATE_ANCIENNETE_TYPE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<AncienneteType> items = new ArrayList<AncienneteType>();
			
			for (AncienneteTypeDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la ancienneteType existe
				AncienneteType entityToSave = null;
				entityToSave = ancienneteTypeRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("ancienneteType id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				if (Utilities.notBlank(dto.getCode())) {
					entityToSave.setCode(dto.getCode());
				}
				if (Utilities.notBlank(dto.getLibelle())) {
					entityToSave.setLibelle(dto.getLibelle());
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
				entityToSave.setUpdatedAt(Utilities.getCurrentDate());
				entityToSave.setUpdatedBy(request.getUser());
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<AncienneteType> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = ancienneteTypeRepository.saveAll((Iterable<AncienneteType>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("ancienneteType", locale));
					response.setHasError(true);
					return response;
				}
				List<AncienneteTypeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AncienneteTypeTransformer.INSTANCE.toLiteDtos(itemsSaved) : AncienneteTypeTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update AncienneteType-----");
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
	 * delete AncienneteType by using AncienneteTypeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AncienneteTypeDto> delete(Request<AncienneteTypeDto> request, Locale locale)  {
		log.info("----begin delete AncienneteType-----");
		
		response = new Response<AncienneteTypeDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.DELETE_ANCIENNETE_TYPE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<AncienneteType> items = new ArrayList<AncienneteType>();
			
			for (AncienneteTypeDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la ancienneteType existe
				AncienneteType existingEntity = null;
				existingEntity = ancienneteTypeRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("ancienneteType -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// anciennete
				Anciennete listOfAnciennete = ancienneteRepository.findByAncienneteTypeId(existingEntity.getId(), false);
				if (listOfAnciennete != null){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAnciennete + ")", locale));
					response.setHasError(true);
					return response;
				}


				existingEntity.setDeletedAt(Utilities.getCurrentDate());
				existingEntity.setDeletedBy(request.getUser());
				existingEntity.setIsDeleted(true);
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				// supprimer les donnees en base
				ancienneteTypeRepository.saveAll((Iterable<AncienneteType>) items);

				response.setHasError(false);
			}

			log.info("----end delete AncienneteType-----");
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
	 * get AncienneteType by using AncienneteTypeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<AncienneteTypeDto> getByCriteria(Request<AncienneteTypeDto> request, Locale locale) {
		log.info("----begin get AncienneteType-----");
		
		response = new Response<AncienneteTypeDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.VIEW_ANCIENNETE_TYPE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<AncienneteType> items = null;
			items = ancienneteTypeRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<AncienneteTypeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AncienneteTypeTransformer.INSTANCE.toLiteDtos(items) : AncienneteTypeTransformer.INSTANCE.toDtos(items);

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
				response.setCount(ancienneteTypeRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("ancienneteType", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get AncienneteType-----");
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
	 * get full AncienneteTypeDto by using AncienneteType as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AncienneteTypeDto getFullInfos(AncienneteTypeDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

}
