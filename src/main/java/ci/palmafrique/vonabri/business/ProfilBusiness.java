



                                                    											

/*
 * Java transformer for entity table profil 
 * Created on 2022-04-25 ( Time 03:17:03 )
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
import ci.palmafrique.vonabri.dao.entity.Profil;
import ci.palmafrique.vonabri.dao.entity.*;
import ci.palmafrique.vonabri.dao.repository.*;

/**
BUSINESS for table "profil"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ProfilBusiness implements IBasicBusiness<Request<ProfilDto>, Response<ProfilDto>> {

	private Response<ProfilDto> response;
	@Autowired
	private ProfilRepository profilRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfilFonctionnaliteRepository profilFonctionnaliteRepository;
	@Autowired
	private FonctionnaliteRepository fonctionnaliteRepository;
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

	public ProfilBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Profil by using ProfilDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ProfilDto> create(Request<ProfilDto> request, Locale locale)  {
		log.info("----begin create Profil-----");
		
		response = new Response<ProfilDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<Profil> items = new ArrayList<Profil>();
			
			for (ProfilDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				//fieldsToVerify.put("code", dto.getCode());
				fieldsToVerify.put("libelle", dto.getLibelle());
				fieldsToVerify.put("datasFonctionnalite", dto.getDatasFonctionnalite());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if profil to insert do not exist
				Profil existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("profil id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}
				dto.setCode(dto.getLibelle());
				// verif unique code in db
				existingEntity = profilRepository.findByCode(dto.getCode(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("profil code -> " + dto.getCode(), locale));
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
				existingEntity = profilRepository.findByLibelle(dto.getLibelle(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("profil libelle -> " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique libelle in items to save
				if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
					response.setHasError(true);
					return response;
				}

				Profil entityToSave = null;
				entityToSave = ProfilTransformer.INSTANCE.toEntity(dto);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				Profil profilSaved = profilRepository.save(entityToSave);
				if (profilSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("profil", locale));
					response.setHasError(true);
					return response;
				}
				items.add(profilSaved);
				if(!dto.getDatasFonctionnalite().isEmpty()) {
					dto.getDatasFonctionnalite().forEach(fonc -> {
						Fonctionnalite foncEntity = fonctionnaliteRepository.findOne(fonc.getId(), false);
						if (profilSaved != null) {
							ProfilFonctionnalite profilFonctionnalite = new ProfilFonctionnalite();
							profilFonctionnalite.setCode(profilSaved.getCode()+"_"+foncEntity.getCode());
							profilFonctionnalite.setLibelle(profilSaved.getLibelle()+"_"+foncEntity.getLibelle());
							profilFonctionnalite.setFonctionnalite(foncEntity);
							profilFonctionnalite.setProfil(profilSaved);
							profilFonctionnalite.setCreatedAt(Utilities.getCurrentDate());
							profilFonctionnalite.setCreatedBy(request.getUser());
							profilFonctionnalite.setIsDeleted(false);
							ProfilFonctionnalite profilFonctionnaliteSaved = profilFonctionnaliteRepository.save(profilFonctionnalite);
						}
					});
				}
			}

			if (!items.isEmpty()) {
				List<Profil> itemsSaved = items;
				// inserer les donnees en base de donnees
//				itemsSaved = profilRepository.saveAll((Iterable<Profil>) items);
//				if (itemsSaved == null) {
//					response.setStatus(functionalError.SAVE_FAIL("profil", locale));
//					response.setHasError(true);
//					return response;
//				}
				List<ProfilDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProfilTransformer.INSTANCE.toLiteDtos(itemsSaved) : ProfilTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create Profil-----");
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
	 * update Profil by using ProfilDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ProfilDto> update(Request<ProfilDto> request, Locale locale)  {
		log.info("----begin update Profil-----");
		
		response = new Response<ProfilDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Profil> items = new ArrayList<Profil>();
			
			for (ProfilDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la profil existe
				Profil entityToSave = null;
				entityToSave = profilRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("profil id -> " + dto.getId(), locale));
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
				Profil profilSaved = profilRepository.save(entityToSave);
				if (profilSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("profil", locale));
					response.setHasError(true);
					return response;
				}
				items.add(profilSaved);
				if(!dto.getDatasFonctionnalite().isEmpty()) {
					dto.getDatasFonctionnalite().forEach(fonc -> {
						Fonctionnalite foncEntity = fonctionnaliteRepository.findOne(fonc.getId(), false);
							ProfilFonctionnalite profilFonctionnalite = new ProfilFonctionnalite();
							profilFonctionnalite.setCode(profilSaved.getCode()+"_"+foncEntity.getCode());
							profilFonctionnalite.setLibelle(profilSaved.getLibelle()+"_"+foncEntity.getLibelle());
							profilFonctionnalite.setFonctionnalite(foncEntity);
							profilFonctionnalite.setProfil(profilSaved);
							profilFonctionnalite.setCreatedAt(Utilities.getCurrentDate());
							profilFonctionnalite.setCreatedBy(request.getUser());
							profilFonctionnalite.setIsDeleted(false);
							ProfilFonctionnalite profilFonctionnaliteSaved = profilFonctionnaliteRepository.save(profilFonctionnalite);
					});
				}
			}

			if (!items.isEmpty()) {
				List<Profil> itemsSaved = items;
				// maj les donnees en base
//				itemsSaved = profilRepository.saveAll((Iterable<Profil>) items);
//				if (itemsSaved == null) {
//					response.setStatus(functionalError.SAVE_FAIL("profil", locale));
//					response.setHasError(true);
//					return response;
//				}
				List<ProfilDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProfilTransformer.INSTANCE.toLiteDtos(itemsSaved) : ProfilTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update Profil-----");
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
	 * delete Profil by using ProfilDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ProfilDto> delete(Request<ProfilDto> request, Locale locale)  {
		log.info("----begin delete Profil-----");
		
		response = new Response<ProfilDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Profil> items = new ArrayList<Profil>();
			
			for (ProfilDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la profil existe
				Profil existingEntity = null;
				existingEntity = profilRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("profil -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// user
				List<User> listOfUser = userRepository.findByProfilId(existingEntity.getId(), false);
				if (listOfUser != null && !listOfUser.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfUser.size() + ")", locale));
					response.setHasError(true);
					return response;
				}
				// profilFonctionnalite
				List<ProfilFonctionnalite> listOfProfilFonctionnalite = profilFonctionnaliteRepository.findByProfilId(existingEntity.getId(), false);
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
				profilRepository.saveAll((Iterable<Profil>) items);

				response.setHasError(false);
			}

			log.info("----end delete Profil-----");
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
	 * get Profil by using ProfilDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<ProfilDto> getByCriteria(Request<ProfilDto> request, Locale locale) {
		log.info("----begin get Profil-----");
		
		response = new Response<ProfilDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Profil> items = null;
			items = profilRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<ProfilDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProfilTransformer.INSTANCE.toLiteDtos(items) : ProfilTransformer.INSTANCE.toDtos(items);

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
				response.setCount(profilRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("profil", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Profil-----");
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
	 * get full ProfilDto by using Profil as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ProfilDto getFullInfos(ProfilDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		List<ProfilFonctionnalite> profilFonctionnalites = profilFonctionnaliteRepository.findByProfilId(dto.getId(), false);
		List<Fonctionnalite> datasFonctionnalite = new ArrayList<Fonctionnalite>();
		if (!profilFonctionnalites.isEmpty()) {
			profilFonctionnalites.forEach(proFunc -> {
				datasFonctionnalite.add(proFunc.getFonctionnalite());
			});
			List<FonctionnaliteDto> datasFonctionnaliteDto = FonctionnaliteTransformer.INSTANCE.toDtos(datasFonctionnalite);
			dto.setDatasFonctionnalite(datasFonctionnaliteDto);
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
