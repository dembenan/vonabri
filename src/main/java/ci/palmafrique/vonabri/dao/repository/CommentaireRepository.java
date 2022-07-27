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
import ci.palmafrique.vonabri.dao.repository.customize._CommentaireRepository;

/**
 * Repository : Commentaire.
 */
@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer>, _CommentaireRepository {
	/**
	 * Finds Commentaire by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Commentaire whose id is equals to the given id. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.id = :id and e.isDeleted = :isDeleted")
	Commentaire findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Commentaire by using description as a search criteria.
	 * 
	 * @param description
	 * @return An Object Commentaire whose description is equals to the given description. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.description = :description and e.isDeleted = :isDeleted")
	List<Commentaire> findByDescription(@Param("description")String description, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Commentaire by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Commentaire whose createdAt is equals to the given createdAt. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Commentaire> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Commentaire by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Commentaire whose deletedAt is equals to the given deletedAt. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Commentaire> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Commentaire by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Commentaire whose updatedAt is equals to the given updatedAt. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Commentaire> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Commentaire by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Commentaire whose createdBy is equals to the given createdBy. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Commentaire> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Commentaire by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Commentaire whose updatedBy is equals to the given updatedBy. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Commentaire> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Commentaire by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Commentaire whose deletedBy is equals to the given deletedBy. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Commentaire> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Commentaire by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Commentaire whose isDeleted is equals to the given isDeleted. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.isDeleted = :isDeleted")
	List<Commentaire> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Commentaire by using commentaireTypeId as a search criteria.
	 * 
	 * @param commentaireTypeId
	 * @return An Object Commentaire whose commentaireTypeId is equals to the given commentaireTypeId. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.commentaireType.id = :commentaireTypeId and e.isDeleted = :isDeleted")
	List<Commentaire> findByCommentaireTypeId(@Param("commentaireTypeId")Integer commentaireTypeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Commentaire by using productionId as a search criteria.
	 * 
	 * @param productionId
	 * @return An Object Commentaire whose productionId is equals to the given productionId. If
	 *         no Commentaire is found, this method returns null.
	 */
	@Query("select e from Commentaire e where e.production.id = :productionId and e.isDeleted = :isDeleted")
	List<Commentaire> findByProductionId(@Param("productionId")Integer productionId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Commentaire by using commentaireDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Commentaire
	 * @throws DataAccessException,ParseException
	 */
	default List<Commentaire> getByCriteria(Request<CommentaireDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Commentaire e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Commentaire> query = em.createQuery(req, Commentaire.class);
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
	 * Finds count of Commentaire by using commentaireDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Commentaire
	 * 
	 */
	default Long count(Request<CommentaireDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Commentaire e where e IS NOT NULL";
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
	default String getWhereExpression(Request<CommentaireDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		CommentaireDto dto = request.getData() != null ? request.getData() : new CommentaireDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (CommentaireDto elt : request.getDatas()) {
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
	default String generateCriteria(CommentaireDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getDescription()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("description", dto.getDescription(), "e.description", "String", dto.getDescriptionParam(), param, index, locale));
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
			if (dto.getCommentaireTypeId()!= null && dto.getCommentaireTypeId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("commentaireTypeId", dto.getCommentaireTypeId(), "e.commentaireType.id", "Integer", dto.getCommentaireTypeIdParam(), param, index, locale));
			}
			if (dto.getProductionId()!= null && dto.getProductionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("productionId", dto.getProductionId(), "e.production.id", "Integer", dto.getProductionIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCommentaireTypeCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("commentaireTypeCode", dto.getCommentaireTypeCode(), "e.commentaireType.code", "String", dto.getCommentaireTypeCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCommentaireTypeLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("commentaireTypeLibelle", dto.getCommentaireTypeLibelle(), "e.commentaireType.libelle", "String", dto.getCommentaireTypeLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
