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
import ci.palmafrique.vonabri.dao.repository.customize._AncienneteRepository;

/**
 * Repository : Anciennete.
 */
@Repository
public interface AncienneteRepository extends JpaRepository<Anciennete, Integer>, _AncienneteRepository {
	/**
	 * Finds Anciennete by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Anciennete whose id is equals to the given id. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.id = :id and e.isDeleted = :isDeleted")
	Anciennete findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Anciennete by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Anciennete whose code is equals to the given code. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.code = :code and e.isDeleted = :isDeleted")
	Anciennete findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Anciennete whose libelle is equals to the given libelle. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Anciennete findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Anciennete whose createdAt is equals to the given createdAt. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Anciennete> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Anciennete whose deletedAt is equals to the given deletedAt. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Anciennete> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Anciennete whose updatedAt is equals to the given updatedAt. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Anciennete> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Anciennete whose createdBy is equals to the given createdBy. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Anciennete> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Anciennete whose updatedBy is equals to the given updatedBy. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Anciennete> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Anciennete whose deletedBy is equals to the given deletedBy. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Anciennete> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Anciennete by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Anciennete whose isDeleted is equals to the given isDeleted. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.isDeleted = :isDeleted")
	List<Anciennete> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Anciennete by using ancienneteTypeId as a search criteria.
	 * 
	 * @param ancienneteTypeId
	 * @return An Object Anciennete whose ancienneteTypeId is equals to the given ancienneteTypeId. If
	 *         no Anciennete is found, this method returns null.
	 */
	@Query("select e from Anciennete e where e.ancienneteType.id = :ancienneteTypeId and e.isDeleted = :isDeleted")
	List<Anciennete> findByAncienneteTypeId(@Param("ancienneteTypeId")Integer ancienneteTypeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Anciennete by using ancienneteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Anciennete
	 * @throws DataAccessException,ParseException
	 */
	default List<Anciennete> getByCriteria(Request<AncienneteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Anciennete e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Anciennete> query = em.createQuery(req, Anciennete.class);
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
	 * Finds count of Anciennete by using ancienneteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Anciennete
	 * 
	 */
	default Long count(Request<AncienneteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Anciennete e where e IS NOT NULL";
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
	default String getWhereExpression(Request<AncienneteDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		AncienneteDto dto = request.getData() != null ? request.getData() : new AncienneteDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AncienneteDto elt : request.getDatas()) {
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
	default String generateCriteria(AncienneteDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
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
			if (dto.getAncienneteTypeId()!= null && dto.getAncienneteTypeId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteTypeId", dto.getAncienneteTypeId(), "e.ancienneteType.id", "Integer", dto.getAncienneteTypeIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAncienneteTypeCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteTypeCode", dto.getAncienneteTypeCode(), "e.ancienneteType.code", "String", dto.getAncienneteTypeCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAncienneteTypeLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteTypeLibelle", dto.getAncienneteTypeLibelle(), "e.ancienneteType.libelle", "String", dto.getAncienneteTypeLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
