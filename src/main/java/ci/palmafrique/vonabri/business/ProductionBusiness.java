



                                                                                                            																									

/*
 * Java transformer for entity table production 
 * Created on 2022-07-27 ( Time 01:31:45 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import ci.palmafrique.vonabri.dao.entity.Commentaire;
import ci.palmafrique.vonabri.dao.entity.CommentaireType;
import ci.palmafrique.vonabri.dao.entity.Livraison;
import ci.palmafrique.vonabri.dao.entity.LivraisonType;
import ci.palmafrique.vonabri.dao.entity.Production;
import ci.palmafrique.vonabri.dao.entity.Site;
import ci.palmafrique.vonabri.dao.entity.StatusFlash;
import ci.palmafrique.vonabri.dao.entity.StockHuile;
import ci.palmafrique.vonabri.dao.entity.Tank;
import ci.palmafrique.vonabri.dao.repository.CommentaireRepository;
import ci.palmafrique.vonabri.dao.repository.CommentaireTypeRepository;
import ci.palmafrique.vonabri.dao.repository.LivraisonRepository;
import ci.palmafrique.vonabri.dao.repository.LivraisonTypeRepository;
import ci.palmafrique.vonabri.dao.repository.ProductionRepository;
import ci.palmafrique.vonabri.dao.repository.SiteRepository;
import ci.palmafrique.vonabri.dao.repository.StatusFlashRepository;
import ci.palmafrique.vonabri.dao.repository.StockHuileRepository;
import ci.palmafrique.vonabri.dao.repository.TankRepository;
import ci.palmafrique.vonabri.dao.repository.UserRepository;
import ci.palmafrique.vonabri.utils.ExceptionUtils;
import ci.palmafrique.vonabri.utils.FunctionalError;
import ci.palmafrique.vonabri.utils.TechnicalError;
import ci.palmafrique.vonabri.utils.Utilities;
import ci.palmafrique.vonabri.utils.Validate;
import ci.palmafrique.vonabri.utils.contract.IBasicBusiness;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.utils.dto.CommentaireDto;
import ci.palmafrique.vonabri.utils.dto.LivraisonDto;
import ci.palmafrique.vonabri.utils.dto.ProductionDto;
import ci.palmafrique.vonabri.utils.dto.SiteDto;
import ci.palmafrique.vonabri.utils.dto.StockHuileDto;
import ci.palmafrique.vonabri.utils.dto.UserDto;
import ci.palmafrique.vonabri.utils.dto.transformer.CommentaireTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.LivraisonTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.ProductionTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.SiteTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.StockHuileTransformer;
import ci.palmafrique.vonabri.utils.enums.FunctionalityEnum;
import ci.palmafrique.vonabri.utils.enums.UtilsEnum;
import lombok.extern.java.Log;

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
	private LivraisonTypeRepository livraisonTypeRepository;
	@Autowired
	private TankRepository tankRepository;
	
	@Autowired
	private StockHuileRepository stockHuileRepository;
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private StatusFlashRepository statusFlashRepository;
	

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
	private DateFormat mediumDateFormat;

	public ProductionBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

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
			List<SiteDto> itemsSiteLivraison =  new ArrayList<SiteDto>();
			List<CommentaireDto> itemsCommentaire =  new ArrayList<CommentaireDto>();
			List<StockHuileDto> itemsStockHuile =  new ArrayList<StockHuileDto>();
			    ProductionDto dto = request.getData();
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("siteId", dto.getSiteId());
				fieldsToVerify.put("statusFlashId", dto.getStatusFlashId());
				fieldsToVerify.put("date", dto.getDate());
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
			    System.out.println("Date FR =====> "+mediumDateFormat.format(dateFormat.parse(dto.getDate())).replace("00:00:00", "") );
			    
				Production productionExisting = productionRepository.findBySiteIdAndDate(dto.getSiteId(), dateFormat.parse(dto.getDate()),false);
				if (productionExisting != null) {
					response.setStatus(functionalError.DATA_EXIST("La Production du " + mediumDateFormat.format(dateFormat.parse(dto.getDate())) +" de l'huilérie "+existingSite.getLibelle()+" à déja été enregistrer : vous pouvez la modifier", locale));
					response.setHasError(true);
					return response;
				}
				// Verify if StatusFlash exist
				StatusFlash existingStatusFlash = null;
				if (dto.getStatusFlashId() != null && dto.getStatusFlashId() > 0){
					existingStatusFlash = statusFlashRepository.findOne(dto.getStatusFlashId(), false);
					if (existingStatusFlash == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("StatusFlash StatusFlashId -> " + dto.getStatusFlashId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				
				if (existingStatusFlash.getCode().equals(UtilsEnum.STATUS_FLASH_SUBMITING.getValue())){
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
					fieldsToVerify.put("itemsSiteLivraisions", dto.getItemsSiteLivraisions() );
					fieldsToVerify.put("itemsStockHuile", dto.getStokPalmiste() );
					
					if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
						response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
						response.setHasError(true);
						return response;
					}
					
				}
				Production entityToSave = null;
				entityToSave = ProductionTransformer.INSTANCE.toEntity(dto, existingSite,existingStatusFlash);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				Production productionSaved = productionRepository.save(entityToSave);
				if (productionSaved == null) {
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
						comToSave = CommentaireTransformer.INSTANCE.toEntity(commentaireDto, existingCommentaireType, productionSaved);
						comToSave.setCreatedAt(Utilities.getCurrentDate());
						comToSave.setCreatedBy(request.getUser());
						comToSave.setIsDeleted(false);
						Commentaire comToSaved = commentaireRepository.save(comToSave);		
						if (comToSaved != null) {
							itemsCommentaire.add(commentaireDto);
						}
					
					}
					
					
				}
				if (dto.getItemsSiteLivraisions() != null && !dto.getItemsSiteLivraisions().isEmpty()) {
					for (SiteDto siteDto : dto.getItemsSiteLivraisions()) {
						List<LivraisonDto> itemsLivraison =  new ArrayList<LivraisonDto>();
						Site siteLivr = siteRepository.findOne(siteDto.getId(), false);
						if (siteLivr == null) {
							log.info("-----------------DONNE INNEXISTANTE SiteId "
									+ siteDto.getId()+ "-------------------");
//							response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//							response.setHasError(true);
//							return response;
						}
						if(siteDto.getLivraisons() != null && !siteDto.getLivraisons().isEmpty()) {
							for (LivraisonDto livraisonDto : siteDto.getLivraisons()) {
								LivraisonType livTyp = livraisonTypeRepository.findOne(livraisonDto.getLivraisonTypeId(),
										false);
								if (livTyp == null) {
									log.info("-----------------DONNE INNEXISTANTE CommentaireTypeId "
											+ livraisonDto.getLivraisonTypeId() + "-------------------");
//									response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//									response.setHasError(true);
//									return response;
								}
								
								Livraison livrToSave = LivraisonTransformer.INSTANCE.toEntity(livraisonDto, productionSaved, siteLivr,livTyp);
								livrToSave.setCreatedAt(Utilities.getCurrentDate());
								livrToSave.setCreatedBy(request.getUser());
								livrToSave.setIsDeleted(false);
								Livraison livrToSaved = livraisonRepository.save(livrToSave);
								if (livrToSaved != null) {
									itemsLivraison.add(livraisonDto);
								}
							}	
						}
						if (!itemsLivraison.isEmpty()) {
							itemsSiteLivraison.add(siteDto);
						}
					}

				}

				if(dto.getItemsStockHuile() != null && !dto.getItemsStockHuile().isEmpty()) {
					
					for (StockHuileDto stockHuileDto : dto.getItemsStockHuile()) {
						Tank tank = tankRepository.findOne(stockHuileDto.getTankId(), false);
						if (tank == null) {
							log.info("-----------------DONNE INNEXISTANTE CommentaireTypeId "+stockHuileDto.getTankId()+"-------------------");
//							response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//							response.setHasError(true);
//							return response;
						}
						StockHuile stockToSave = null;
						stockToSave = StockHuileTransformer.INSTANCE.toEntity(stockHuileDto, productionSaved, tank);
						stockToSave.setCreatedAt(Utilities.getCurrentDate());
						stockToSave.setCreatedBy(request.getUser());
						stockToSave.setIsDeleted(false);
						StockHuile stockToSaved = stockHuileRepository.save(stockToSave);		
						if (stockToSaved != null) {
							itemsStockHuile.add(stockHuileDto);
						}
					
					}
					
					
				}
				ProductionDto productionDto = ProductionTransformer.INSTANCE.toDto(productionSaved);
				productionDto.setItemsCommentaire(itemsCommentaire);
				productionDto.setItemsSiteLivraisions(itemsSiteLivraison);
				productionDto.setItemsStockHuile(itemsStockHuile);
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
	public Response<ProductionDto> update(Request<ProductionDto> request, Locale locale) {
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

			Response<UserDto> userResponse = userBusiness.isGranted(request,
					FunctionalityEnum.UPDATE_PRODUCTION.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Production> items = new ArrayList<Production>();
			List<SiteDto> itemsSiteLivraison =  new ArrayList<SiteDto>();
			List<CommentaireDto> itemsCommentaire =  new ArrayList<CommentaireDto>();
			List<StockHuileDto> itemsStockHuile =  new ArrayList<StockHuileDto>();
			ProductionDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			fieldsToVerify.put("statusFlashId", dto.getStatusFlashId());
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
			if (dto.getSiteId() != null && dto.getSiteId() > 0) {
				Site existingSite = siteRepository.findOne(dto.getSiteId(), false);
				if (existingSite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setSite(existingSite);
			}

			// Verify if StatusFlash exist
			if (dto.getStatusFlashId() != null && dto.getStatusFlashId() > 0) {
				StatusFlash existingStatusFlash = statusFlashRepository.findOne(dto.getStatusFlashId(), false);
				if (existingStatusFlash == null) {
					response.setStatus(functionalError
							.DATA_NOT_EXIST("StatusFlash StatusFlashId -> " + dto.getStatusFlashId(), locale));
					response.setHasError(true);
					return response;
				}
				if (existingStatusFlash.getCode().equals(UtilsEnum.STATUS_FLASH_SUBMITING.getValue())) {
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

					fieldsToVerify.put("itemsCommentaire", dto.getItemsCommentaire());
					fieldsToVerify.put("itemsSiteLivraisions", dto.getItemsSiteLivraisions());
					fieldsToVerify.put("itemsStockHuile", dto.getStokPalmiste());

					if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
						response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
						response.setHasError(true);
						return response;
					}

				}

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

			if (dto.getItemsCommentaire() != null && !dto.getItemsCommentaire().isEmpty()) {

				for (CommentaireDto commentaireDto : dto.getItemsCommentaire()) {
					CommentaireType existingCommentaireType = commentaireTypeRepository
							.findOne(commentaireDto.getCommentaireTypeId(), false);
					if (existingCommentaireType == null) {
						log.info("-----------------DONNE INNEXISTANTE CommentaireTypeId "
								+ commentaireDto.getCommentaireTypeId() + "-------------------");
//							response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//							response.setHasError(true);
//							return response;
					}
					Commentaire comToSave = null;
					comToSave = CommentaireTransformer.INSTANCE.toEntity(commentaireDto, existingCommentaireType,
							entityToSave);
					comToSave.setCreatedAt(Utilities.getCurrentDate());
					comToSave.setCreatedBy(request.getUser());
					comToSave.setIsDeleted(false);
					Commentaire comToSaved = commentaireRepository.save(comToSave);
					if (comToSaved != null) {
						itemsCommentaire.add(commentaireDto);
					}

				}

			}
			if (dto.getItemsSiteLivraisions() != null && !dto.getItemsSiteLivraisions().isEmpty()) {
				for (SiteDto siteDto : dto.getItemsSiteLivraisions()) {
					List<LivraisonDto> itemsLivraison =  new ArrayList<LivraisonDto>();
					Site siteLivr = siteRepository.findOne(siteDto.getId(), false);
					if (siteLivr == null) {
						log.info("-----------------DONNE INNEXISTANTE SiteId "
								+ siteDto.getId()+ "-------------------");
//						response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//						response.setHasError(true);
//						return response;
					}
					if(siteDto.getLivraisons() != null && !siteDto.getLivraisons().isEmpty()) {
						for (LivraisonDto livraisonDto : siteDto.getLivraisons()) {
							LivraisonType livTyp = livraisonTypeRepository.findOne(livraisonDto.getLivraisonTypeId(),
									false);
							if (livTyp == null) {
								log.info("-----------------DONNE INNEXISTANTE CommentaireTypeId "
										+ livraisonDto.getLivraisonTypeId() + "-------------------");
//								response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//								response.setHasError(true);
//								return response;
							}
							
							Livraison livrToSave = LivraisonTransformer.INSTANCE.toEntity(livraisonDto, entityToSave, siteLivr,livTyp);
							livrToSave.setCreatedAt(Utilities.getCurrentDate());
							livrToSave.setCreatedBy(request.getUser());
							livrToSave.setIsDeleted(false);
							Livraison livrToSaved = livraisonRepository.save(livrToSave);
							if (livrToSaved != null) {
								itemsLivraison.add(livraisonDto);
							}
						}	
					}
					if (!itemsLivraison.isEmpty()) {
						itemsSiteLivraison.add(siteDto);
					}
				}

			}

			if (dto.getItemsStockHuile() != null && !dto.getItemsStockHuile().isEmpty()) {

				for (StockHuileDto stockHuileDto : dto.getItemsStockHuile()) {
					Tank tank = tankRepository.findOne(stockHuileDto.getTankId(), false);
					if (tank == null) {
						log.info("-----------------DONNE INNEXISTANTE CommentaireTypeId " + stockHuileDto.getTankId()
								+ "-------------------");
//							response.setStatus(functionalError.DATA_NOT_EXIST("CommentaireType CommentaireTypeId -> " + commentaireDto.getCommentaireTypeId(), locale));
//							response.setHasError(true);
//							return response;
					}
					StockHuile stockToSave = null;
					stockToSave = StockHuileTransformer.INSTANCE.toEntity(stockHuileDto, entityToSave, tank);
					stockToSave.setCreatedAt(Utilities.getCurrentDate());
					stockToSave.setCreatedBy(request.getUser());
					stockToSave.setIsDeleted(false);
					StockHuile stockToSaved = stockHuileRepository.save(stockToSave);
					if (stockToSaved != null) {
						itemsStockHuile.add(stockHuileDto);
					}

				}

			}

			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			Production productionSaved = productionRepository.save(entityToSave);
			if (productionSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("Production", locale));
				response.setHasError(true);
				return response;
			}
			ProductionDto productionDto = ProductionTransformer.INSTANCE.toDto(productionSaved);
			productionDto.setItemsCommentaire(itemsCommentaire);
			productionDto.setItemsSiteLivraisions(itemsSiteLivraison);
			productionDto.setItemsStockHuile(itemsStockHuile);
			ProductionDto item = getFullInfos(productionDto, 1, request.getIsSimpleLoading(), locale);
			response.setItem(item);
			response.setHasError(false);
			

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
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
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
		List<SiteDto> itemsSiteLivraison =  new ArrayList<SiteDto>();
		List<CommentaireDto> itemsCommentaire =  new ArrayList<CommentaireDto>();
		List<StockHuileDto> itemsStockHuile =  new ArrayList<StockHuileDto>();
		
		
		
			List<Site> allSite = siteRepository.findByIsDeleted(false);
			if(allSite != null) {
				List<SiteDto> allSiteDtos = SiteTransformer.INSTANCE.toDtos(allSite);
				for (SiteDto siteDto : allSiteDtos) {
					List<Livraison> livraisonsSite = livraisonRepository.findByProductionIdAndSiteId(dto.getId(),
							siteDto.getId(), false);
					if (!livraisonsSite.isEmpty()) {
						List<LivraisonDto> livrDtos = LivraisonTransformer.INSTANCE.toDtos(livraisonsSite);
						siteDto.setLivraisons(livrDtos);
						itemsSiteLivraison.add(siteDto);
					}
				}
				dto.setItemsSiteLivraisions(itemsSiteLivraison);
			}

		
		
		
		List<Commentaire> coms = commentaireRepository.findByProductionId(dto.getId(), false);
		if(!coms.isEmpty()) {
			List<CommentaireDto> comsDto = CommentaireTransformer.INSTANCE.toDtos(coms);
			dto.setItemsCommentaire(comsDto);
		}
		List<StockHuile> stocks = stockHuileRepository.findByProductionId(dto.getId(), false);
		if(!stocks.isEmpty()) {
			List<StockHuileDto> stocksDto = StockHuileTransformer.INSTANCE.toDtos(stocks);
			dto.setItemsStockHuile(stocksDto);
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
