



                                                                														

/*
 * Java transformer for entity table element 
 * Created on 2022-05-05 ( Time 15:22:34 )
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
import ci.palmafrique.vonabri.dao.entity.Element;
import ci.palmafrique.vonabri.dao.entity.ElementType;
import ci.palmafrique.vonabri.dao.entity.Element;
import ci.palmafrique.vonabri.dao.entity.*;
import ci.palmafrique.vonabri.dao.repository.*;

/**
BUSINESS for table "element"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ElementBusiness implements IBasicBusiness<Request<ElementDto>, Response<ElementDto>> {

	private Response<ElementDto> response;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ElementTypeRepository elementTypeRepository;

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

	public ElementBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Element by using ElementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ElementDto> create(Request<ElementDto> request, Locale locale)  {
		log.info("----begin create Element-----");
		
		response = new Response<ElementDto>();
		
		try {


			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_ELEMENT.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<Element> items = new ArrayList<Element>();
			
			for (ElementDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("code", dto.getCode());
				fieldsToVerify.put("libelle", dto.getLibelle());
				//fieldsToVerify.put("icon", dto.getIcon());
				//fieldsToVerify.put("parentId", dto.getParentId());
				fieldsToVerify.put("elementTypeId", dto.getElementTypeId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if element to insert do not exist
				Element existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("element id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// verif unique code in db
				existingEntity = elementRepository.findByCode(dto.getCode(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("element code -> " + dto.getCode(), locale));
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
				existingEntity = elementRepository.findByLibelle(dto.getLibelle(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("element libelle -> " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique libelle in items to save
				if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
					response.setHasError(true);
					return response;
				}

				// Verify if elementType exist
				ElementType existingElementType = null;
				if (dto.getElementTypeId() != null && dto.getElementTypeId() > 0){
					existingElementType = elementTypeRepository.findOne(dto.getElementTypeId(), false);
					if (existingElementType == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("elementType elementTypeId -> " + dto.getElementTypeId(), locale));
						response.setHasError(true);
						return response;
					}
					if(existingElementType.getCode().equals(UtilsEnum.ELEMENT_TYPE_FORMULAIRE.getValue())) {
						fieldsToVerify.put("titre", dto.getTitre());
						fieldsToVerify.put("champsListing", dto.getChampsListing());
						fieldsToVerify.put("champsCreation", dto.getChampsCreation());
						if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
							response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
							response.setHasError(true);
							return response;
						}
					}
				}
				
				// Verify if element exist
				Element existingElement = null;
				if (dto.getParentId() != null && dto.getParentId() > 0){
					existingElement = elementRepository.findOne(dto.getParentId(), false);
					if (existingElement == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("element parentId -> " + dto.getParentId(), locale));
						response.setHasError(true);
						return response;
					}
				}

				Element entityToSave = null;
				entityToSave = ElementTransformer.INSTANCE.toEntity(dto, existingElementType, existingElement);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				Element elementSaved = elementRepository.save(entityToSave);
				if(elementSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("element  -> "+dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				items.add(elementSaved);
				if(dto.getDatasChildren() != null && !dto.getDatasChildren().isEmpty()) {
					for(ElementDto childDto:dto.getDatasChildren()) {
						Element entityToSaveChild = null;
						entityToSaveChild = ElementTransformer.INSTANCE.toEntity(childDto, existingElementType, elementSaved);
						entityToSaveChild.setCreatedAt(Utilities.getCurrentDate());
						entityToSaveChild.setCreatedBy(request.getUser());
						entityToSaveChild.setIsDeleted(false);
						elementRepository.save(entityToSaveChild);
					}
				}
			}

			if (!items.isEmpty()) {
				List<Element> itemsSaved = items;
				// inserer les donnees en base de donnees
//				itemsSaved = elementRepository.saveAll((Iterable<Element>) items);
//				if (itemsSaved == null) {
//					response.setStatus(functionalError.SAVE_FAIL("element", locale));
//					response.setHasError(true);
//					return response;
//				}
				List<ElementDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ElementTransformer.INSTANCE.toLiteDtos(itemsSaved) : ElementTransformer.INSTANCE.toDtos(itemsSaved);
				
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
				Integer index= request.getIndex();
					List<ElementDto> itemsUnique = hierarchicalFormat(itemsDto);
					if (Utilities.isNotEmpty(itemsUnique)) {
						
						itemsUnique.sort((e1,e2) -> e2.getId().compareTo(e1.getId()));
						final int sizeUnique = itemsUnique.size();
						List<ElementDto> itemsPaginner = Utilities.paginner(itemsUnique, index, size);
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

			log.info("----end create Element-----");
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
	 * update Element by using ElementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ElementDto> update(Request<ElementDto> request, Locale locale)  {
		log.info("----begin update Element-----");
		
		response = new Response<ElementDto>();
		
		try {


			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Element> items = new ArrayList<Element>();
			
			for (ElementDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la element existe
				Element entityToSave = null;
				entityToSave = elementRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("element id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if elementType exist
				if (dto.getElementTypeId() != null && dto.getElementTypeId() > 0){
					ElementType existingElementType = elementTypeRepository.findOne(dto.getElementTypeId(), false);
					if (existingElementType == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("elementType elementTypeId -> " + dto.getElementTypeId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setElementType(existingElementType);
				}
				// Verify if element exist
				if (dto.getParentId() != null && dto.getParentId() > 0){
					Element existingElement = elementRepository.findOne(dto.getParentId(), false);
					if (existingElement == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("element parentId -> " + dto.getParentId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setElement(existingElement);
				}
				if (Utilities.notBlank(dto.getCode())) {
					entityToSave.setCode(dto.getCode());
				}
				if (Utilities.notBlank(dto.getLibelle())) {
					entityToSave.setLibelle(dto.getLibelle());
				}
				if (Utilities.notBlank(dto.getIcon())) {
					entityToSave.setIcon(dto.getIcon());
				}
				
				if (Utilities.notBlank(dto.getTitre())) {
					entityToSave.setTitre(dto.getTitre());
				}
				if (Utilities.notBlank(dto.getChampsListing() )) {
					entityToSave.setChampsListing(dto.getChampsListing());
				}
				if (Utilities.notBlank(dto.getChampsCreation() )) {
					entityToSave.setChampsCreation(dto.getChampsCreation());
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
				if(dto.getDatasChildren() != null && !dto.getDatasChildren().isEmpty()) {
					for(ElementDto childDto:dto.getDatasChildren()) {
						Element entityToSaveChild = null;
						entityToSaveChild = ElementTransformer.INSTANCE.toEntity(childDto, entityToSave.getElementType(), entityToSave);
						entityToSaveChild.setCreatedAt(Utilities.getCurrentDate());
						entityToSaveChild.setCreatedBy(request.getUser());
						entityToSaveChild.setIsDeleted(false);
						elementRepository.save(entityToSaveChild);
					}
				}
			}

			if (!items.isEmpty()) {
				List<Element> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = elementRepository.saveAll((Iterable<Element>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("element", locale));
					response.setHasError(true);
					return response;
				}
				List<ElementDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ElementTransformer.INSTANCE.toLiteDtos(itemsSaved) : ElementTransformer.INSTANCE.toDtos(itemsSaved);

				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
						
						System.out.println("dto.listing  "+dto.getChampsListing());
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

			log.info("----end update Element-----");
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
	 * delete Element by using ElementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ElementDto> delete(Request<ElementDto> request, Locale locale)  {
		log.info("----begin delete Element-----");
		
		response = new Response<ElementDto>();
		
		try {


			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_USER.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Element> items = new ArrayList<Element>();
			
			for (ElementDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la element existe
				Element existingEntity = null;
				existingEntity = elementRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("element -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// element
				List<Element> listOfElement = elementRepository.findByParentId(existingEntity.getId(), false);
				if (listOfElement != null && !listOfElement.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfElement.size() + ")", locale));
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
				elementRepository.saveAll((Iterable<Element>) items);

				response.setHasError(false);
			}

			log.info("----end delete Element-----");
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
	 * get Element by using ElementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<ElementDto> getByCriteria(Request<ElementDto> request, Locale locale) {
		log.info("----begin get Element-----");
		
		response = new Response<ElementDto>();
		
		try {

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.VIEW_ELEMENT.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Element> items = null;
			items = elementRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<ElementDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ElementTransformer.INSTANCE.toLiteDtos(items) : ElementTransformer.INSTANCE.toDtos(items);

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
					List<ElementDto> itemsUnique = hierarchicalFormat(itemsDto);
					if (Utilities.isNotEmpty(itemsUnique)) {
						
						itemsUnique.sort((e1,e2) -> e2.getId().compareTo(e1.getId()));
						
						final int sizeUnique = itemsUnique.size();
						List<ElementDto> itemsPaginner = Utilities.paginner(itemsUnique, index, size);
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
				response.setCount(elementRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("fonctionnalite", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Element-----");
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

	public static List<ElementDto> hierarchicalFormat(List<ElementDto> itemsProductTypeDto) {
		  boolean allDone = false;
		  List<ElementDto> singletons = new ArrayList<ElementDto>();
		  while (!allDone) {
		   allDone = true;
		   List<ElementDto> productTypesWhithoutChildren = new ArrayList<ElementDto>();
		   for (ElementDto productType : itemsProductTypeDto) {
		    boolean hasChildren = false;
		    for (ElementDto otherProductType : itemsProductTypeDto) {
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
		    for (ElementDto productType : productTypesWhithoutChildren) {
		     boolean parentFounded = false;
		     for (ElementDto parent : itemsProductTypeDto) {
		      if (parent.getId() == productType.getParentId()) {
		       parentFounded = true;
		       List<ElementDto> children;
		       if (parent.getDatasChildren() == null || parent.getDatasChildren().isEmpty())
		        children = new ArrayList<ElementDto>();
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
	 * get full ElementDto by using Element as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ElementDto getFullInfos(ElementDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
