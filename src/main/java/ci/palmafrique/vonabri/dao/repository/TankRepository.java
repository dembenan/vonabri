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
import ci.palmafrique.vonabri.dao.repository.customize._TankRepository;

/**
 * Repository : Tank.
 */
@Repository
public interface TankRepository extends JpaRepository<Tank, Integer>, _TankRepository {
	/**
	 * Finds Tank by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Tank whose id is equals to the given id. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.id = :id and e.isDeleted = :isDeleted")
	Tank findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Tank by using siteId as a search criteria.
	 * 
	 * @param siteId
	 * @return An Object Tank whose siteId is equals to the given siteId. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.site.id = :siteId and e.isDeleted = :isDeleted")
	List<Tank> findBySiteId(@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Tank whose code is equals to the given code. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.code = :code and e.isDeleted = :isDeleted")
	Tank findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	
	
	@Query("select e from Tank e where e.code = :code and e.site.id = :siteId and e.isDeleted = :isDeleted")
	Tank findByCodeAndSiteId(@Param("code")String code,@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);
	

	/**
	 * Finds Tank by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Tank whose libelle is equals to the given libelle. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Tank findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	
	@Query("select e from Tank e where e.libelle = :libelle and e.site.id = :siteId and e.isDeleted = :isDeleted")
	Tank findByLibelleAndSiteId(@Param("libelle")String libelle,@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Tank whose createdAt is equals to the given createdAt. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Tank> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Tank whose deletedAt is equals to the given deletedAt. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Tank> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Tank whose updatedAt is equals to the given updatedAt. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Tank> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Tank whose createdBy is equals to the given createdBy. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Tank> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Tank whose updatedBy is equals to the given updatedBy. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Tank> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Tank whose deletedBy is equals to the given deletedBy. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Tank> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Tank by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Tank whose isDeleted is equals to the given isDeleted. If
	 *         no Tank is found, this method returns null.
	 */
	@Query("select e from Tank e where e.isDeleted = :isDeleted")
	List<Tank> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Tank by using tankDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Tank
	 * @throws DataAccessException,ParseException
	 */
	default List<Tank> getByCriteria(Request<TankDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Tank e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Tank> query = em.createQuery(req, Tank.class);
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
	 * Finds count of Tank by using tankDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Tank
	 * 
	 */
	default Long count(Request<TankDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Tank e where e IS NOT NULL";
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
	default String getWhereExpression(Request<TankDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		TankDto dto = request.getData() != null ? request.getData() : new TankDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (TankDto elt : request.getDatas()) {
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
	default String generateCriteria(TankDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getSiteId()!= null && dto.getSiteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteId", dto.getSiteId(), "e.site.id", "Integer", dto.getSiteIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteLibelle", dto.getSiteLibelle(), "e.site.libelle", "String", dto.getSiteLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
