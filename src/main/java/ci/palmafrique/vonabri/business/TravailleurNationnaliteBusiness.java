



                                                    											

/*
 * Java transformer for entity table travailleur_nationnalite 
 * Created on 2022-06-11 ( Time 19:24:50 )
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
import ci.palmafrique.vonabri.dao.entity.TravailleurNationnalite;
import ci.palmafrique.vonabri.dao.entity.Nationnalite;
import ci.palmafrique.vonabri.dao.entity.Travailleur;
import ci.palmafrique.vonabri.dao.entity.*;
import ci.palmafrique.vonabri.dao.repository.*;

/**
BUSINESS for table "travailleur_nationnalite"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class TravailleurNationnaliteBusiness implements IBasicBusiness<Request<TravailleurNationnaliteDto>, Response<TravailleurNationnaliteDto>> {

	private Response<TravailleurNationnaliteDto> response;
	@Autowired
	private TravailleurNationnaliteRepository travailleurNationnaliteRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NationnaliteRepository nationnaliteRepository;
	@Autowired
	private TravailleurRepository travailleurRepository;

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

	public TravailleurNationnaliteBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create TravailleurNationnalite by using TravailleurNationnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<TravailleurNationnaliteDto> create(Request<TravailleurNationnaliteDto> request, Locale locale)  {
		log.info("----begin create TravailleurNationnalite-----");
		
		response = new Response<TravailleurNationnaliteDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_TRAVAILLEUR_NATIONNALITE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<TravailleurNationnalite> items = new ArrayList<TravailleurNationnalite>();
			
			for (TravailleurNationnaliteDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("travailleurId", dto.getTravailleurId());
				fieldsToVerify.put("nationnaliteId", dto.getNationnaliteId());
				fieldsToVerify.put("deletedAt", dto.getDeletedAt());
				fieldsToVerify.put("deletedBy", dto.getDeletedBy());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if travailleurNationnalite to insert do not exist
				TravailleurNationnalite existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("travailleurNationnalite id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if nationnalite exist
				Nationnalite existingNationnalite = null;
				if (dto.getNationnaliteId() != null && dto.getNationnaliteId() > 0){
					existingNationnalite = nationnaliteRepository.findOne(dto.getNationnaliteId(), false);
					if (existingNationnalite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("nationnalite nationnaliteId -> " + dto.getNationnaliteId(), locale));
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
				}
				TravailleurNationnalite entityToSave = null;
				entityToSave = TravailleurNationnaliteTransformer.INSTANCE.toEntity(dto, existingNationnalite, existingTravailleur);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<TravailleurNationnalite> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = travailleurNationnaliteRepository.saveAll((Iterable<TravailleurNationnalite>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("travailleurNationnalite", locale));
					response.setHasError(true);
					return response;
				}
				List<TravailleurNationnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravailleurNationnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : TravailleurNationnaliteTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create TravailleurNationnalite-----");
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
	 * update TravailleurNationnalite by using TravailleurNationnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<TravailleurNationnaliteDto> update(Request<TravailleurNationnaliteDto> request, Locale locale)  {
		log.info("----begin update TravailleurNationnalite-----");
		
		response = new Response<TravailleurNationnaliteDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.UPDATE_TRAVAILLEUR_NATIONNALITE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<TravailleurNationnalite> items = new ArrayList<TravailleurNationnalite>();
			
			for (TravailleurNationnaliteDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la travailleurNationnalite existe
				TravailleurNationnalite entityToSave = null;
				entityToSave = travailleurNationnaliteRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("travailleurNationnalite id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if nationnalite exist
				if (dto.getNationnaliteId() != null && dto.getNationnaliteId() > 0){
					Nationnalite existingNationnalite = nationnaliteRepository.findOne(dto.getNationnaliteId(), false);
					if (existingNationnalite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("nationnalite nationnaliteId -> " + dto.getNationnaliteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setNationnalite(existingNationnalite);
				}
				// Verify if travailleur exist
				if (dto.getTravailleurId() != null && dto.getTravailleurId() > 0){
					Travailleur existingTravailleur = travailleurRepository.findOne(dto.getTravailleurId(), false);
					if (existingTravailleur == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("travailleur travailleurId -> " + dto.getTravailleurId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setTravailleur(existingTravailleur);
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
				List<TravailleurNationnalite> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = travailleurNationnaliteRepository.saveAll((Iterable<TravailleurNationnalite>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("travailleurNationnalite", locale));
					response.setHasError(true);
					return response;
				}
				List<TravailleurNationnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravailleurNationnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : TravailleurNationnaliteTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update TravailleurNationnalite-----");
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
	 * delete TravailleurNationnalite by using TravailleurNationnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<TravailleurNationnaliteDto> delete(Request<TravailleurNationnaliteDto> request, Locale locale)  {
		log.info("----begin delete TravailleurNationnalite-----");
		
		response = new Response<TravailleurNationnaliteDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.DELETE_TRAVAILLEUR_NATIONNALITE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<TravailleurNationnalite> items = new ArrayList<TravailleurNationnalite>();
			
			for (TravailleurNationnaliteDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la travailleurNationnalite existe
				TravailleurNationnalite existingEntity = null;
				existingEntity = travailleurNationnaliteRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("travailleurNationnalite -> " + dto.getId(), locale));
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
				travailleurNationnaliteRepository.saveAll((Iterable<TravailleurNationnalite>) items);

				response.setHasError(false);
			}

			log.info("----end delete TravailleurNationnalite-----");
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
	 * get TravailleurNationnalite by using TravailleurNationnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<TravailleurNationnaliteDto> getByCriteria(Request<TravailleurNationnaliteDto> request, Locale locale) {
		log.info("----begin get TravailleurNationnalite-----");
		
		response = new Response<TravailleurNationnaliteDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.VIEW_TRAVAILLEUR_NATIONNALITE.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<TravailleurNationnalite> items = null;
			items = travailleurNationnaliteRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<TravailleurNationnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravailleurNationnaliteTransformer.INSTANCE.toLiteDtos(items) : TravailleurNationnaliteTransformer.INSTANCE.toDtos(items);

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
				response.setCount(travailleurNationnaliteRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("travailleurNationnalite", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get TravailleurNationnalite-----");
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
	 * get full TravailleurNationnaliteDto by using TravailleurNationnalite as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private TravailleurNationnaliteDto getFullInfos(TravailleurNationnaliteDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
