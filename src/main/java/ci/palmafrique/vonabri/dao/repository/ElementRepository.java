package ci.palmafrique.vonabri.dao.repository;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.palmafrique.vonabri.utils.*;
import ci.palmafrique.vonabri.utils.dto.*;
import ci.palmafrique.vonabri.utils.contract.*;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.dao.entity.*;
import ci.palmafrique.vonabri.dao.repository.customize._ElementRepository;

/**
 * Repository : Element.
 */
@Repository
public interface ElementRepository extends JpaRepository<Element, Integer>, _ElementRepository {
	/**
	 * Finds Element by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Element whose id is equals to the given id. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.id = :id and e.isDeleted = :isDeleted")
	Element findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Element by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Element whose code is equals to the given code. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.code = :code and e.isDeleted = :isDeleted")
	Element findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Element whose libelle is equals to the given libelle. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Element findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using icon as a search criteria.
	 * 
	 * @param icon
	 * @return An Object Element whose icon is equals to the given icon. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.icon = :icon and e.isDeleted = :isDeleted")
	List<Element> findByIcon(@Param("icon")String icon, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using titre as a search criteria.
	 * 
	 * @param titre
	 * @return An Object Element whose titre is equals to the given titre. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.titre = :titre and e.isDeleted = :isDeleted")
	List<Element> findByTitre(@Param("titre")String titre, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using champsListing as a search criteria.
	 * 
	 * @param champsListing
	 * @return An Object Element whose champsListing is equals to the given champsListing. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.champsListing = :champsListing and e.isDeleted = :isDeleted")
	List<Element> findByChampsListing(@Param("champsListing")String champsListing, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using champsCreation as a search criteria.
	 * 
	 * @param champsCreation
	 * @return An Object Element whose champsCreation is equals to the given champsCreation. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.champsCreation = :champsCreation and e.isDeleted = :isDeleted")
	List<Element> findByChampsCreation(@Param("champsCreation")String champsCreation, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Element whose createdAt is equals to the given createdAt. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Element> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Element whose deletedAt is equals to the given deletedAt. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Element> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Element whose updatedAt is equals to the given updatedAt. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Element> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Element whose createdBy is equals to the given createdBy. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Element> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Element whose updatedBy is equals to the given updatedBy. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Element> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Element whose deletedBy is equals to the given deletedBy. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Element> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Element by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Element whose isDeleted is equals to the given isDeleted. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.isDeleted = :isDeleted")
	List<Element> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Element by using elementTypeId as a search criteria.
	 * 
	 * @param elementTypeId
	 * @return An Object Element whose elementTypeId is equals to the given elementTypeId. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.elementType.id = :elementTypeId and e.isDeleted = :isDeleted")
	List<Element> findByElementTypeId(@Param("elementTypeId")Integer elementTypeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Element by using parentId as a search criteria.
	 * 
	 * @param parentId
	 * @return An Object Element whose parentId is equals to the given parentId. If
	 *         no Element is found, this method returns null.
	 */
	@Query("select e from Element e where e.element.id = :parentId and e.isDeleted = :isDeleted")
	List<Element> findByParentId(@Param("parentId")Integer parentId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Element by using elementDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Element
	 * @throws DataAccessException,ParseException
	 */
	default List<Element> getByCriteria(Request<ElementDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Element e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Element> query = em.createQuery(req, Element.class);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(request.getSize());
		}
		return query.getResultList();
	}

	/**
	 * Finds count of Element by using elementDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Element
	 * 
	 */
	default Long count(Request<ElementDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Element e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by  e.id desc";
		javax.persistence.Query query = em.createQuery(req);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Long count = (Long) query.getResultList().get(0);
		return count;
	}

	/**
	 * get where expression
	 * @param request
	 * @param param
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String getWhereExpression(Request<ElementDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		ElementDto dto = request.getData() != null ? request.getData() : new ElementDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (ElementDto elt : request.getDatas()) {
				elt.setIsDeleted(false);
				String eltReq = generateCriteria(elt, param, index, locale);
				if (request.getIsAnd() != null && request.getIsAnd()) {
					othersReq += "and (" + eltReq + ") ";
				} else {
					othersReq += "or (" + eltReq + ") ";
				}
				index++;
			}
		}
		String req = "";
		if (!mainReq.isEmpty()) {
			req += " and (" + mainReq + ") ";
		}
		req += othersReq;
		return req;
	}

	/**
	 * generate sql query for dto
	 * @param dto
	 * @param param
	 * @param index
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String generateCriteria(ElementDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getIcon())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("icon", dto.getIcon(), "e.icon", "String", dto.getIconParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTitre())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("titre", dto.getTitre(), "e.titre", "String", dto.getTitreParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getChampsListing())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("champsListing", dto.getChampsListing(), "e.champsListing", "String", dto.getChampsListingParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getChampsCreation())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("champsCreation", dto.getChampsCreation(), "e.champsCreation", "String", dto.getChampsCreationParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getElementTypeId()!= null && dto.getElementTypeId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("elementTypeId", dto.getElementTypeId(), "e.elementType.id", "Integer", dto.getElementTypeIdParam(), param, index, locale));
			}
			if (dto.getParentId()!= null && dto.getParentId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentId", dto.getParentId(), "e.element.id", "Integer", dto.getParentIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getElementTypeCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("elementTypeCode", dto.getElementTypeCode(), "e.elementType.code", "String", dto.getElementTypeCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getElementTypeLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("elementTypeLibelle", dto.getElementTypeLibelle(), "e.elementType.libelle", "String", dto.getElementTypeLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getElementCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("elementCode", dto.getElementCode(), "e.element.code", "String", dto.getElementCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getElementLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("elementLibelle", dto.getElementLibelle(), "e.element.libelle", "String", dto.getElementLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
