



                                                                                                            																									

/*
 * Java transformer for entity table production 
 * Created on 2022-07-27 ( Time 01:31:45 )
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
import ci.palmafrique.vonabri.dao.entity.*;
import ci.palmafrique.vonabri.dao.repository.*;

/**
BUSINESS for table "production"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ProductionBusiness implements IBasicBusiness<Request<ProductionDto>, Response<ProductionDto>> {

	private Response<ProductionDto> response;
	@Autowired
	private ProductionRepository productionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentaireRepository commentaireRepository;
	@Autowired
	private CommentaireTypeRepository commentaireTypeRepository;
	@Autowired
	private LivraisonRepository livraisonRepository;
	@Autowired
	private StockHuileRepository stockHuileRepository;
	@Autowired
	private SiteRepository siteRepository;

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

	public ProductionBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Production by using ProductionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ProductionDto> create(Request<ProductionDto> request, Locale locale)  {
		log.info("----begin create Production-----");
		
		response = new Response<ProductionDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_PRODUCTION.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<Production> items = new ArrayList<Production>();
			List<CommentaireDto> itemCom =  new ArrayList<CommentaireDto>();

			    ProductionDto dto = request.getData();
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("siteId", dto.getSiteId());
				fieldsToVerify.put("date", dto.getDate());
				fieldsToVerify.put("aciditeHuile", dto.getAciditeHuile());
				fieldsToVerify.put("regimesMalEgra", dto.getRegimesMalEgra());
				fieldsToVerify.put("cagesCarreau", dto.getCagesCarreau());
				fieldsToVerify.put("pi", dto.getPi());
				fieldsToVerify.put("pvp", dto.getPvp());
				fieldsToVerify.put("sortieHuile", dto.getSortieHuile());
				fieldsToVerify.put("sortiePalmiste", dto.getSortiePalmiste());
				fieldsToVerify.put("stokPalmiste", dto.getStokPalmiste());
				fieldsToVerify.put("regimesTraiter", dto.getRegimesTraiter());
				fieldsToVerify.put("productionHuile", dto.getProductionHuile());
				fieldsToVerify.put("productionPalmiste", dto.getProductionPalmiste());
				fieldsToVerify.put("tauxExtractionHuile", dto.getTauxExtractionHuile());
				fieldsToVerify.put("tauxExtractionPalmiste", dto.getTauxExtractionPalmiste());
				fieldsToVerify.put("sortieHuileDeSarci", dto.getSortieHuileDeSarci());
				
				fieldsToVerify.put("itemsCommentaire", dto.getItemsCommentaire() );
				fieldsToVerify.put("itemsLivraisions", dto.getItemsLivraisions() );
				fieldsToVerify.put("itemsStockHuile", dto.getStokPalmiste() );
				
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if production to insert do not exist
				Production existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("production id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if site exist
				Site existingSite = null;
				if (dto.getSiteId() != null && dto.getSiteId() > 0){
					existingSite = siteRepository.findOne(dto.getSiteId(), false);
					if (existingSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				Production entityToSave = null;
				entityToSave = ProductionTransformer.INSTANCE.toEntity(dto, existingSite);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				Production itemSaved = productionRepository.save(entityToSave);
				if (itemSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("Production", locale));
					response.setHasError(true);
					return response;
				}
				
				if(dto.getItemsCommentaire() != null && !dto.getItemsCommentaire().isEmpty()) {
					
					for (CommentaireDto commentaireDto : dto.getItemsCommentaire()) {
						CommentaireType existingCommentaireType = commentaireTypeRepository.findOne(commentaireDto.getCommentaireTypeId(), false);
						if (existingCommentaireType == null) {
							log.info("-----------------DONNE INNEXISTANTE CommentaireTypeId "+commentaireDto.getCommentaireTypeId()+"-------------------");
//							response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//							response.setHasError(true);
//							return response;
						}
						Commentaire comToSave = null;
						comToSave = CommentaireTransformer.INSTANCE.toEntity(commentaireDto, existingCommentaireType, itemSaved);
						comToSave.setCreatedAt(Utilities.getCurrentDate());
						comToSave.setCreatedBy(request.getUser());
						comToSave.setIsDeleted(false);
						Commentaire comToSaved = commentaireRepository.save(comToSave);		
						if (comToSaved != null) {
							itemCom.add(commentaireDto);
						}
					
					}
					
					
				}
//				if(dto.getItemsLivraisions() != null && !dto.getItemsLivraisions().isEmpty()) {
//					
//					for (LivraisonDto livraisonDto : dto.getItemsLivraisions()) {
//						CommentaireType existingCommentaireType = commentaireTypeRepository.findOne(commentaireDto.getCommentaireTypeId(), false);
//						if (existingCommentaireType == null) {
//							log.info("-----------------DONNE INNEXISTANTE CommentaireTypeId "+commentaireDto.getCommentaireTypeId()+"-------------------");
////							response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
////							response.setHasError(true);
////							return response;
//						}
//						Livraison comToSave = null;
//						comToSave = CommentaireTransformer.INSTANCE.toEntity(commentaireDto, existingCommentaireType, itemSaved);
//						comToSave.setCreatedAt(Utilities.getCurrentDate());
//						comToSave.setCreatedBy(request.getUser());
//						comToSave.setIsDeleted(false);
//						Commentaire comToSaved = commentaireRepository.save(comToSave);		
//						if (comToSaved != null) {
//							itemCom.add(commentaireDto);
//						}
//					
//					}
//					
//					
//				}

				
				ProductionDto productionDto = ProductionTransformer.INSTANCE.toDto(itemSaved);
				productionDto.setItemsCommentaire(itemCom);
				ProductionDto item = getFullInfos(productionDto, 1, request.getIsSimpleLoading(), locale);
				response.setItem(item);
				response.setHasError(false);

			log.info("----end create Production-----");
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
	 * update Production by using ProductionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ProductionDto> update(Request<ProductionDto> request, Locale locale)  {
		log.info("----begin update Production-----");
		
		response = new Response<ProductionDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.UPDATE_PRODUCTION.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Production> items = new ArrayList<Production>();
			
			for (ProductionDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la production existe
				Production entityToSave = null;
				entityToSave = productionRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("production id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if site exist
				if (dto.getSiteId() != null && dto.getSiteId() > 0){
					Site existingSite = siteRepository.findOne(dto.getSiteId(), false);
					if (existingSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setSite(existingSite);
				}
				if (Utilities.notBlank(dto.getDate())) {
					entityToSave.setDate(dateFormat.parse(dto.getDate()));
				}
				if (dto.getAciditeHuile() != null && dto.getAciditeHuile() > 0) {
					entityToSave.setAciditeHuile(dto.getAciditeHuile());
				}
				if (dto.getRegimesMalEgra() != null && dto.getRegimesMalEgra() > 0) {
					entityToSave.setRegimesMalEgra(dto.getRegimesMalEgra());
				}
				if (Utilities.notBlank(dto.getCagesCarreau())) {
					entityToSave.setCagesCarreau(dto.getCagesCarreau());
				}
				if (dto.getPi() != null && dto.getPi() > 0) {
					entityToSave.setPi(dto.getPi());
				}
				if (dto.getPvp() != null && dto.getPvp() > 0) {
					entityToSave.setPvp(dto.getPvp());
				}
				if (dto.getSortieHuile() != null && dto.getSortieHuile() > 0) {
					entityToSave.setSortieHuile(dto.getSortieHuile());
				}
				if (dto.getSortiePalmiste() != null && dto.getSortiePalmiste() > 0) {
					entityToSave.setSortiePalmiste(dto.getSortiePalmiste());
				}
				if (dto.getStokPalmiste() != null && dto.getStokPalmiste() > 0) {
					entityToSave.setStokPalmiste(dto.getStokPalmiste());
				}
				if (dto.getRegimesTraiter() != null && dto.getRegimesTraiter() > 0) {
					entityToSave.setRegimesTraiter(dto.getRegimesTraiter());
				}
				if (dto.getProductionHuile() != null && dto.getProductionHuile() > 0) {
					entityToSave.setProductionHuile(dto.getProductionHuile());
				}
				if (dto.getProductionPalmiste() != null && dto.getProductionPalmiste() > 0) {
					entityToSave.setProductionPalmiste(dto.getProductionPalmiste());
				}
				if (dto.getTauxExtractionHuile() != null && dto.getTauxExtractionHuile() > 0) {
					entityToSave.setTauxExtractionHuile(dto.getTauxExtractionHuile());
				}
				if (dto.getTauxExtractionPalmiste() != null && dto.getTauxExtractionPalmiste() > 0) {
					entityToSave.setTauxExtractionPalmiste(dto.getTauxExtractionPalmiste());
				}
				if (dto.getSortieHuileDeSarci() != null && dto.getSortieHuileDeSarci() > 0) {
					entityToSave.setSortieHuileDeSarci(dto.getSortieHuileDeSarci());
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
				List<Production> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = productionRepository.saveAll((Iterable<Production>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("production", locale));
					response.setHasError(true);
					return response;
				}
				List<ProductionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductionTransformer.INSTANCE.toLiteDtos(itemsSaved) : ProductionTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update Production-----");
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
	 * delete Production by using ProductionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ProductionDto> delete(Request<ProductionDto> request, Locale locale)  {
		log.info("----begin delete Production-----");
		
		response = new Response<ProductionDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.DELETE_PRODUCTION.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Production> items = new ArrayList<Production>();
			
			for (ProductionDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la production existe
				Production existingEntity = null;
				existingEntity = productionRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("production -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// commentaire
				List<Commentaire> listOfCommentaire = commentaireRepository.findByProductionId(existingEntity.getId(), false);
				if (listOfCommentaire != null && !listOfCommentaire.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfCommentaire.size() + ")", locale));
					response.setHasError(true);
					return response;
				}
				// livraison
				List<Livraison> listOfLivraison = livraisonRepository.findByProductionId(existingEntity.getId(), false);
				if (listOfLivraison != null && !listOfLivraison.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfLivraison.size() + ")", locale));
					response.setHasError(true);
					return response;
				}
				// stockHuile
				List<StockHuile> listOfStockHuile = stockHuileRepository.findByProductionId(existingEntity.getId(), false);
				if (listOfStockHuile != null && !listOfStockHuile.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfStockHuile.size() + ")", locale));
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
				productionRepository.saveAll((Iterable<Production>) items);

				response.setHasError(false);
			}

			log.info("----end delete Production-----");
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
	 * get Production by using ProductionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<ProductionDto> getByCriteria(Request<ProductionDto> request, Locale locale) {
		log.info("----begin get Production-----");
		
		response = new Response<ProductionDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.VIEW_PRODUCTION.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Production> items = null;
			items = productionRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<ProductionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductionTransformer.INSTANCE.toLiteDtos(items) : ProductionTransformer.INSTANCE.toDtos(items);

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
				response.setCount(productionRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("production", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Production-----");
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
	 * get full ProductionDto by using Production as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ProductionDto getFullInfos(ProductionDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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