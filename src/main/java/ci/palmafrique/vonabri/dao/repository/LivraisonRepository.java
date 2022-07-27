package ci.palmafrique.vonabri.dao.repository;

import java.util.Date;
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
import ci.palmafrique.vonabri.dao.repository.customize._LivraisonRepository;

/**
 * Repository : Livraison.
 */
@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Integer>, _LivraisonRepository {
	/**
	 * Finds Livraison by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Livraison whose id is equals to the given id. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.id = :id and e.isDeleted = :isDeleted")
	Livraison findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Livraison by using stockLivrer as a search criteria.
	 * 
	 * @param stockLivrer
	 * @return An Object Livraison whose stockLivrer is equals to the given stockLivrer. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.stockLivrer = :stockLivrer and e.isDeleted = :isDeleted")
	List<Livraison> findByStockLivrer(@Param("stockLivrer")Double stockLivrer, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Livraison whose createdAt is equals to the given createdAt. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Livraison> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Livraison whose deletedAt is equals to the given deletedAt. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Livraison> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Livraison whose updatedAt is equals to the given updatedAt. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Livraison> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Livraison whose createdBy is equals to the given createdBy. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Livraison> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Livraison whose updatedBy is equals to the given updatedBy. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Livraison> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Livraison whose deletedBy is equals to the given deletedBy. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Livraison> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Livraison whose isDeleted is equals to the given isDeleted. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.isDeleted = :isDeleted")
	List<Livraison> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Livraison by using productionId as a search criteria.
	 * 
	 * @param productionId
	 * @return An Object Livraison whose productionId is equals to the given productionId. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.production.id = :productionId and e.isDeleted = :isDeleted")
	List<Livraison> findByProductionId(@Param("productionId")Integer productionId, @Param("isDeleted")Boolean isDeleted);

	
	@Query("select e from Livraison e where e.production.id = :productionId and e.site.id = :siteId and e.isDeleted = :isDeleted")
	List<Livraison> findByProductionIdAndSiteId(@Param("productionId")Integer productionId,@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livraison by using siteId as a search criteria.
	 * 
	 * @param siteId
	 * @return An Object Livraison whose siteId is equals to the given siteId. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.site.id = :siteId and e.isDeleted = :isDeleted")
	List<Livraison> findBySiteId(@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Livraison by using livraisonTypeId as a search criteria.
	 * 
	 * @param livraisonTypeId
	 * @return An Object Livraison whose livraisonTypeId is equals to the given livraisonTypeId. If
	 *         no Livraison is found, this method returns null.
	 */
	@Query("select e from Livraison e where e.livraisonType.id = :livraisonTypeId and e.isDeleted = :isDeleted")
	List<Livraison> findByLivraisonTypeId(@Param("livraisonTypeId")Integer livraisonTypeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Livraison by using livraisonDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Livraison
	 * @throws DataAccessException,ParseException
	 */
	default List<Livraison> getByCriteria(Request<LivraisonDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Livraison e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Livraison> query = em.createQuery(req, Livraison.class);
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
	 * Finds count of Livraison by using livraisonDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Livraison
	 * 
	 */
	default Long count(Request<LivraisonDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Livraison e where e IS NOT NULL";
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
	default String getWhereExpression(Request<LivraisonDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		LivraisonDto dto = request.getData() != null ? request.getData() : new LivraisonDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (LivraisonDto elt : request.getDatas()) {
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
	default String generateCriteria(LivraisonDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getStockLivrer()!= null && dto.getStockLivrer() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("stockLivrer", dto.getStockLivrer(), "e.stockLivrer", "Double", dto.getStockLivrerParam(), param, index, locale));
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
			if (dto.getProductionId()!= null && dto.getProductionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("productionId", dto.getProductionId(), "e.production.id", "Integer", dto.getProductionIdParam(), param, index, locale));
			}
			if (dto.getSiteId()!= null && dto.getSiteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteId", dto.getSiteId(), "e.site.id", "Integer", dto.getSiteIdParam(), param, index, locale));
			}
			if (dto.getLivraisonTypeId()!= null && dto.getLivraisonTypeId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("livraisonTypeId", dto.getLivraisonTypeId(), "e.livraisonType.id", "Integer", dto.getLivraisonTypeIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteCode", dto.getSiteCode(), "e.site.code", "String", dto.getSiteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteLibelle", dto.getSiteLibelle(), "e.site.libelle", "String", dto.getSiteLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLivraisonTypeCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("livraisonTypeCode", dto.getLivraisonTypeCode(), "e.livraisonType.code", "String", dto.getLivraisonTypeCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLivraisonTypeLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("livraisonTypeLibelle", dto.getLivraisonTypeLibelle(), "e.livraisonType.libelle", "String", dto.getLivraisonTypeLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
