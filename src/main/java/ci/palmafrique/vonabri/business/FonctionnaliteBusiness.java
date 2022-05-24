



                                                                														

/*
 * Java transformer for entity table fonctionnalite 
 * Created on 2022-04-25 ( Time 03:17:01 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.business;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ci.palmafrique.vonabri.dao.entity.Fonctionnalite;
import ci.palmafrique.vonabri.dao.entity.ProfilFonctionnalite;
import ci.palmafrique.vonabri.dao.repository.FonctionnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.ProfilFonctionnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.UserRepository;
import ci.palmafrique.vonabri.utils.ExceptionUtils;
import ci.palmafrique.vonabri.utils.FunctionalError;
import ci.palmafrique.vonabri.utils.TechnicalError;
import ci.palmafrique.vonabri.utils.Utilities;
import ci.palmafrique.vonabri.utils.Validate;
import ci.palmafrique.vonabri.utils.contract.IBasicBusiness;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.utils.dto.FonctionnaliteDto;
import ci.palmafrique.vonabri.utils.dto.transformer.FonctionnaliteTransformer;
import lombok.extern.java.Log;

/**
BUSINESS for table "fonctionnalite"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class FonctionnaliteBusiness implements IBasicBusiness<Request<FonctionnaliteDto>, Response<FonctionnaliteDto>> {

	private Response<FonctionnaliteDto> response;
	@Autowired
	private FonctionnaliteRepository fonctionnaliteRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfilFonctionnaliteRepository profilFonctionnaliteRepository;

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

	public FonctionnaliteBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<FonctionnaliteDto> create(Request<FonctionnaliteDto> request, Locale locale)  {
		log.info("----begin create Fonctionnalite-----");
		
		response = new Response<FonctionnaliteDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.CREATE_FONCTIONNALITE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}
			
			List<Fonctionnalite> items = new ArrayList<Fonctionnalite>();
			
			for (FonctionnaliteDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("code", dto.getCode());
				fieldsToVerify.put("name", dto.getName());
				//fieldsToVerify.put("parentCode", dto.getParentCode());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if fonctionnalite to insert do not exist
				Fonctionnalite existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("fonctionnalite id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// verif unique code in db
				existingEntity = fonctionnaliteRepository.findByCode(dto.getCode(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("fonctionnalite code -> " + dto.getCode(), locale));
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
				existingEntity = fonctionnaliteRepository.findByLibelle(dto.getLibelle(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("fonctionnalite libelle -> " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique libelle in items to save
				if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
					response.setHasError(true);
					return response;
				}

				// Verify if fonctionnalite exist
//				Fonctionnalite existingFonctionnalite = null;
//				if (dto.getParentId() != null && dto.getParentId() > 0){
//					existingFonctionnalite = fonctionnaliteRepository.findOne(dto.getParentId(), false);
//					if (existingFonctionnalite == null) {
//						response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite parentId -> " + dto.getParentId(), locale));
//						response.setHasError(true);
//						return response;
//					}
//				}
				// verif unique libelle in db
				Fonctionnalite existingParent = null;
				if (Utilities.notBlank(dto.getParentCode())) {
					existingParent = fonctionnaliteRepository.findByLibelle(dto.getParentCode(), false);
					if (existingParent != null) {
						response.setStatus(functionalError.DATA_EXIST("fonctionnalite parent code -> " +dto.getParentCode(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if fonctionnaliteType exist
//				FonctionnaliteType existingFonctionnaliteType = null;
//				if (dto.getFonctionnaliteTypeId() != null && dto.getFonctionnaliteTypeId() > 0){
//					existingFonctionnaliteType = fonctionnaliteTypeRepository.findOne(dto.getFonctionnaliteTypeId(), false);
//					if (existingFonctionnaliteType == null) {
//						response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnaliteType fonctionnaliteTypeId -> " + dto.getFonctionnaliteTypeId(), locale));
//						response.setHasError(true);
//						return response;
//					}
//				}
				dto.setLibelle(dto.getName());
				Fonctionnalite entityToSave = null;
				entityToSave = FonctionnaliteTransformer.INSTANCE.toEntity(dto, existingParent);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<Fonctionnalite> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = fonctionnaliteRepository.saveAll((Iterable<Fonctionnalite>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("fonctionnalite", locale));
					response.setHasError(true);
					return response;
				}
				List<FonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : FonctionnaliteTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create Fonctionnalite-----");
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
	 * update Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<FonctionnaliteDto> update(Request<FonctionnaliteDto> request, Locale locale)  {
		log.info("----begin update Fonctionnalite-----");
		
		response = new Response<FonctionnaliteDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}

//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.UPDATE_FONCTIONNALITE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Fonctionnalite> items = new ArrayList<Fonctionnalite>();
			
			for (FonctionnaliteDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la fonctionnalite existe
				Fonctionnalite entityToSave = null;
				entityToSave = fonctionnaliteRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if fonctionnalite exist
				if (dto.getParentId() != null && dto.getParentId() > 0){
					Fonctionnalite existingFonctionnalite = fonctionnaliteRepository.findOne(dto.getParentId(), false);
					if (existingFonctionnalite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite parentId -> " + dto.getParentId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setFonctionnalite(existingFonctionnalite);
				}
				// Verify if fonctionnaliteType exist
//				if (dto.getFonctionnaliteTypeId() != null && dto.getFonctionnaliteTypeId() > 0){
//					FonctionnaliteType existingFonctionnaliteType = fonctionnaliteTypeRepository.findOne(dto.getFonctionnaliteTypeId(), false);
//					if (existingFonctionnaliteType == null) {
//						response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnaliteType fonctionnaliteTypeId -> " + dto.getFonctionnaliteTypeId(), locale));
//						response.setHasError(true);
//						return response;
//					}
//					entityToSave.setFonctionnaliteType(existingFonctionnaliteType);
//				}
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
				List<Fonctionnalite> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = fonctionnaliteRepository.saveAll((Iterable<Fonctionnalite>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("fonctionnalite", locale));
					response.setHasError(true);
					return response;
				}
				List<FonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : FonctionnaliteTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update Fonctionnalite-----");
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
	 * delete Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<FonctionnaliteDto> delete(Request<FonctionnaliteDto> request, Locale locale)  {
		log.info("----begin delete Fonctionnalite-----");
		
		response = new Response<FonctionnaliteDto>();
		
		try {

//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}

//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.DELETE_FONCTIONNALITE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Fonctionnalite> items = new ArrayList<Fonctionnalite>();
			
			for (FonctionnaliteDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la fonctionnalite existe
				Fonctionnalite existingEntity = null;
				existingEntity = fonctionnaliteRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// fonctionnalite
				List<Fonctionnalite> listOfFonctionnalite = fonctionnaliteRepository.findByParentId(existingEntity.getId(), false);
				if (listOfFonctionnalite != null && !listOfFonctionnalite.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfFonctionnalite.size() + ")", locale));
					response.setHasError(true);
					return response;
				}
				// profilFonctionnalite
				List<ProfilFonctionnalite> listOfProfilFonctionnalite = profilFonctionnaliteRepository.findByFonctionnaliteId(existingEntity.getId(), false);
				if (listOfProfilFonctionnalite != null && !listOfProfilFonctionnalite.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfProfilFonctionnalite.size() + ")", locale));
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
				fonctionnaliteRepository.saveAll((Iterable<Fonctionnalite>) items);

				response.setHasError(false);
			}

			log.info("----end delete Fonctionnalite-----");
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
	 * get Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<FonctionnaliteDto> getByCriteria(Request<FonctionnaliteDto> request, Locale locale) {
		log.info("----begin get Fonctionnalite-----");
		
		response = new Response<FonctionnaliteDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}

//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.VIEW_FONCTIONNALITE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Fonctionnalite> items = null;
			items = fonctionnaliteRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<FonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionnaliteTransformer.INSTANCE.toLiteDtos(items) : FonctionnaliteTransformer.INSTANCE.toDtos(items);

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
				Integer index= request.getIndex();
				if (request.getHierarchyFormat() != null && request.getHierarchyFormat()) {
					List<FonctionnaliteDto> itemsUnique = hierarchicalFormat(itemsDto);
					if (Utilities.isNotEmpty(itemsUnique)) {
						
						itemsUnique.sort((e1,e2) -> e2.getId().compareTo(e1.getId()));
						
						final int sizeUnique = itemsUnique.size();
						List<FonctionnaliteDto> itemsPaginner = Utilities.paginner(itemsUnique, index, size);
						response.setItems(itemsPaginner);
						response.setCount((long)sizeUnique);
						response.setHasError(false);
						return response;
					}else {
						response.setStatus(functionalError.DATA_EMPTY("famille", locale));
						response.setHasError(false);
						return response;
					}
				}
				response.setItems(itemsDto);
				response.setCount(fonctionnaliteRepository.count(request, em, locale));
				response.setHasError(false);
			} else {

				response.setStatus(functionalError.DATA_EMPTY("fonctionnalite", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Fonctionnalite-----");
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

	public static List<FonctionnaliteDto> hierarchicalFormat(List<FonctionnaliteDto> itemsProductTypeDto) {
		  boolean allDone = false;
		  List<FonctionnaliteDto> singletons = new ArrayList<FonctionnaliteDto>();
		  while (!allDone) {
		   allDone = true;
		   List<FonctionnaliteDto> productTypesWhithoutChildren = new ArrayList<FonctionnaliteDto>();
		   for (FonctionnaliteDto productType : itemsProductTypeDto) {
		    boolean hasChildren = false;
		    for (FonctionnaliteDto otherProductType : itemsProductTypeDto) {
		     if (productType != otherProductType) {
		      if (otherProductType.getParentId() != null
		        && otherProductType.getParentId() == productType.getId()) {
		       hasChildren = true;
		       allDone = false;
		       break;
		      }
		     }
		    }
		    if (!hasChildren) {
		     productTypesWhithoutChildren.add(productType);
		    }
		   }
		   if (!productTypesWhithoutChildren.isEmpty()) {
		    itemsProductTypeDto.removeAll(productTypesWhithoutChildren);
		    // mettre checque élément sans enfant dans son eventuel parent
		    for (FonctionnaliteDto productType : productTypesWhithoutChildren) {
		     boolean parentFounded = false;
		     for (FonctionnaliteDto parent : itemsProductTypeDto) {
		      if (parent.getId() == productType.getParentId()) {
		       parentFounded = true;
		       List<FonctionnaliteDto> children;
		       if (parent.getDatasChildren() == null || parent.getDatasChildren().isEmpty())
		        children = new ArrayList<FonctionnaliteDto>();
		       else
		        children = parent.getDatasChildren();
		       children.add(productType);
		       parent.setDatasChildren(children);
		       break;
		      }
		     }
		     if (!parentFounded)
		      singletons.add(productType);
		    }
		   }
		  }

		  // return itemsProductTypeDto;
		  return singletons;
		 }
	/**
	 * get full FonctionnaliteDto by using Fonctionnalite as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private FonctionnaliteDto getFullInfos(FonctionnaliteDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		Fonctionnalite existingParent = null;
		if(dto.getParentId() != null && dto.getParentId() > 0) {
		    existingParent = fonctionnaliteRepository.findOne(dto.getParentId(), false);
		    if(existingParent != null) {
				FonctionnaliteDto itemDto = FonctionnaliteTransformer.INSTANCE.toDto(existingParent);
				List<FonctionnaliteDto> fonctionnalites = new ArrayList<FonctionnaliteDto>();;
				fonctionnalites.add(itemDto);
				fonctionnalites.add(dto);
				List<FonctionnaliteDto> fonctionnalitesDto = hierarchicalFormat(fonctionnalites);
				log.info("fonctionnalites" + fonctionnalitesDto);

		    }
			
		}

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

}
