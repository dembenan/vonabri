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
import ci.palmafrique.vonabri.dao.repository.customize._CommentaireTypeRepository;

/**
 * Repository : CommentaireType.
 */
@Repository
public interface CommentaireTypeRepository extends JpaRepository<CommentaireType, Integer>, _CommentaireTypeRepository {
	/**
	 * Finds CommentaireType by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object CommentaireType whose id is equals to the given id. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.id = :id and e.isDeleted = :isDeleted")
	CommentaireType findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds CommentaireType by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object CommentaireType whose code is equals to the given code. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.code = :code and e.isDeleted = :isDeleted")
	CommentaireType findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object CommentaireType whose libelle is equals to the given libelle. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	CommentaireType findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object CommentaireType whose createdAt is equals to the given createdAt. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<CommentaireType> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object CommentaireType whose deletedAt is equals to the given deletedAt. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<CommentaireType> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object CommentaireType whose updatedAt is equals to the given updatedAt. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<CommentaireType> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object CommentaireType whose createdBy is equals to the given createdBy. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<CommentaireType> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object CommentaireType whose updatedBy is equals to the given updatedBy. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<CommentaireType> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object CommentaireType whose deletedBy is equals to the given deletedBy. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<CommentaireType> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CommentaireType by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object CommentaireType whose isDeleted is equals to the given isDeleted. If
	 *         no CommentaireType is found, this method returns null.
	 */
	@Query("select e from CommentaireType e where e.isDeleted = :isDeleted")
	List<CommentaireType> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of CommentaireType by using commentaireTypeDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of CommentaireType
	 * @throws DataAccessException,ParseException
	 */
	default List<CommentaireType> getByCriteria(Request<CommentaireTypeDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from CommentaireType e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<CommentaireType> query = em.createQuery(req, CommentaireType.class);
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
	 * Finds count of CommentaireType by using commentaireTypeDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of CommentaireType
	 * 
	 */
	default Long count(Request<CommentaireTypeDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from CommentaireType e where e IS NOT NULL";
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
	default String getWhereExpression(Request<CommentaireTypeDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		CommentaireTypeDto dto = request.getData() != null ? request.getData() : new CommentaireTypeDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (CommentaireTypeDto elt : request.getDatas()) {
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
	default String generateCriteria(CommentaireTypeDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
