



                                                        												

/*
 * Java transformer for entity table sous_poste_de_travail 
 * Created on 2022-06-11 ( Time 19:24:48 )
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
import ci.palmafrique.vonabri.dao.entity.SousPosteDeTravail;
import ci.palmafrique.vonabri.dao.entity.PosteDeTravail;
import ci.palmafrique.vonabri.dao.entity.*;
import ci.palmafrique.vonabri.dao.repository.*;

/**
BUSINESS for table "sous_poste_de_travail"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class SousPosteDeTravailBusiness implements IBasicBusiness<Request<SousPosteDeTravailDto>, Response<SousPosteDeTravailDto>> {

	private Response<SousPosteDeTravailDto> response;
	@Autowired
	private SousPosteDeTravailRepository sousPosteDeTravailRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PosteDeTravailRepository posteDeTravailRepository;
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

	public SousPosteDeTravailBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create SousPosteDeTravail by using SousPosteDeTravailDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<SousPosteDeTravailDto> create(Request<SousPosteDeTravailDto> request, Locale locale)  {
		log.info("----begin create SousPosteDeTravail-----");
		
		response = new Response<SousPosteDeTravailDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_SOUS_POSTE_DE_TRAVAIL.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<SousPosteDeTravail> items = new ArrayList<SousPosteDeTravail>();
			
			for (SousPosteDeTravailDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("code", dto.getCode());
				fieldsToVerify.put("libelle", dto.getLibelle());
				fieldsToVerify.put("posteDeTravailId", dto.getPosteDeTravailId());
				fieldsToVerify.put("deletedAt", dto.getDeletedAt());
				fieldsToVerify.put("deletedBy", dto.getDeletedBy());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if sousPosteDeTravail to insert do not exist
				SousPosteDeTravail existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("sousPosteDeTravail id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// verif unique code in db
				existingEntity = sousPosteDeTravailRepository.findByCode(dto.getCode(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("sousPosteDeTravail code -> " + dto.getCode(), locale));
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
				existingEntity = sousPosteDeTravailRepository.findByLibelle(dto.getLibelle(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("sousPosteDeTravail libelle -> " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique libelle in items to save
				if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
					response.setHasError(true);
					return response;
				}

				// Verify if posteDeTravail exist
				PosteDeTravail existingPosteDeTravail = null;
				if (dto.getPosteDeTravailId() != null && dto.getPosteDeTravailId() > 0){
					existingPosteDeTravail = posteDeTravailRepository.findOne(dto.getPosteDeTravailId(), false);
					if (existingPosteDeTravail == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("posteDeTravail posteDeTravailId -> " + dto.getPosteDeTravailId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				SousPosteDeTravail entityToSave = null;
				entityToSave = SousPosteDeTravailTransformer.INSTANCE.toEntity(dto, existingPosteDeTravail);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<SousPosteDeTravail> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = sousPosteDeTravailRepository.saveAll((Iterable<SousPosteDeTravail>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("sousPosteDeTravail", locale));
					response.setHasError(true);
					return response;
				}
				List<SousPosteDeTravailDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SousPosteDeTravailTransformer.INSTANCE.toLiteDtos(itemsSaved) : SousPosteDeTravailTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create SousPosteDeTravail-----");
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
	 * update SousPosteDeTravail by using SousPosteDeTravailDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<SousPosteDeTravailDto> update(Request<SousPosteDeTravailDto> request, Locale locale)  {
		log.info("----begin update SousPosteDeTravail-----");
		
		response = new Response<SousPosteDeTravailDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.UPDATE_SOUS_POSTE_DE_TRAVAIL.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<SousPosteDeTravail> items = new ArrayList<SousPosteDeTravail>();
			
			for (SousPosteDeTravailDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la sousPosteDeTravail existe
				SousPosteDeTravail entityToSave = null;
				entityToSave = sousPosteDeTravailRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("sousPosteDeTravail id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if posteDeTravail exist
				if (dto.getPosteDeTravailId() != null && dto.getPosteDeTravailId() > 0){
					PosteDeTravail existingPosteDeTravail = posteDeTravailRepository.findOne(dto.getPosteDeTravailId(), false);
					if (existingPosteDeTravail == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("posteDeTravail posteDeTravailId -> " + dto.getPosteDeTravailId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setPosteDeTravail(existingPosteDeTravail);
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
				List<SousPosteDeTravail> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = sousPosteDeTravailRepository.saveAll((Iterable<SousPosteDeTravail>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("sousPosteDeTravail", locale));
					response.setHasError(true);
					return response;
				}
				List<SousPosteDeTravailDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SousPosteDeTravailTransformer.INSTANCE.toLiteDtos(itemsSaved) : SousPosteDeTravailTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update SousPosteDeTravail-----");
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
	 * delete SousPosteDeTravail by using SousPosteDeTravailDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<SousPosteDeTravailDto> delete(Request<SousPosteDeTravailDto> request, Locale locale)  {
		log.info("----begin delete SousPosteDeTravail-----");
		
		response = new Response<SousPosteDeTravailDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.DELETE_SOUS_POSTE_DE_TRAVAIL.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<SousPosteDeTravail> items = new ArrayList<SousPosteDeTravail>();
			
			for (SousPosteDeTravailDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la sousPosteDeTravail existe
				SousPosteDeTravail existingEntity = null;
				existingEntity = sousPosteDeTravailRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("sousPosteDeTravail -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// travailleur
				List<Travailleur> listOfTravailleur = travailleurRepository.findBySousPosteDeTravailId(existingEntity.getId(), false);
				if (listOfTravailleur != null && !listOfTravailleur.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTravailleur.size() + ")", locale));
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
				sousPosteDeTravailRepository.saveAll((Iterable<SousPosteDeTravail>) items);

				response.setHasError(false);
			}

			log.info("----end delete SousPosteDeTravail-----");
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
	 * get SousPosteDeTravail by using SousPosteDeTravailDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<SousPosteDeTravailDto> getByCriteria(Request<SousPosteDeTravailDto> request, Locale locale) {
		log.info("----begin get SousPosteDeTravail-----");
		
		response = new Response<SousPosteDeTravailDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.VIEW_SOUS_POSTE_DE_TRAVAIL.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<SousPosteDeTravail> items = null;
			items = sousPosteDeTravailRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<SousPosteDeTravailDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SousPosteDeTravailTransformer.INSTANCE.toLiteDtos(items) : SousPosteDeTravailTransformer.INSTANCE.toDtos(items);

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
				response.setCount(sousPosteDeTravailRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("sousPosteDeTravail", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get SousPosteDeTravail-----");
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
	 * get full SousPosteDeTravailDto by using SousPosteDeTravail as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private SousPosteDeTravailDto getFullInfos(SousPosteDeTravailDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
