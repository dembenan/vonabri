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
import ci.palmafrique.vonabri.dao.repository.customize._TravailleurNationnaliteRepository;

/**
 * Repository : TravailleurNationnalite.
 */
@Repository
public interface TravailleurNationnaliteRepository extends JpaRepository<TravailleurNationnalite, Integer>, _TravailleurNationnaliteRepository {
	/**
	 * Finds TravailleurNationnalite by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object TravailleurNationnalite whose id is equals to the given id. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.id = :id and e.isDeleted = :isDeleted")
	TravailleurNationnalite findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds TravailleurNationnalite by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object TravailleurNationnalite whose createdAt is equals to the given createdAt. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TravailleurNationnalite by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object TravailleurNationnalite whose deletedAt is equals to the given deletedAt. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TravailleurNationnalite by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object TravailleurNationnalite whose updatedAt is equals to the given updatedAt. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TravailleurNationnalite by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object TravailleurNationnalite whose createdBy is equals to the given createdBy. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TravailleurNationnalite by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object TravailleurNationnalite whose updatedBy is equals to the given updatedBy. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TravailleurNationnalite by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object TravailleurNationnalite whose deletedBy is equals to the given deletedBy. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TravailleurNationnalite by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object TravailleurNationnalite whose isDeleted is equals to the given isDeleted. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds TravailleurNationnalite by using nationnaliteId as a search criteria.
	 * 
	 * @param nationnaliteId
	 * @return An Object TravailleurNationnalite whose nationnaliteId is equals to the given nationnaliteId. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.nationnalite.id = :nationnaliteId and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByNationnaliteId(@Param("nationnaliteId")Integer nationnaliteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds TravailleurNationnalite by using travailleurId as a search criteria.
	 * 
	 * @param travailleurId
	 * @return An Object TravailleurNationnalite whose travailleurId is equals to the given travailleurId. If
	 *         no TravailleurNationnalite is found, this method returns null.
	 */
	@Query("select e from TravailleurNationnalite e where e.travailleur.id = :travailleurId and e.isDeleted = :isDeleted")
	List<TravailleurNationnalite> findByTravailleurId(@Param("travailleurId")Integer travailleurId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of TravailleurNationnalite by using travailleurNationnaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of TravailleurNationnalite
	 * @throws DataAccessException,ParseException
	 */
	default List<TravailleurNationnalite> getByCriteria(Request<TravailleurNationnaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from TravailleurNationnalite e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<TravailleurNationnalite> query = em.createQuery(req, TravailleurNationnalite.class);
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
	 * Finds count of TravailleurNationnalite by using travailleurNationnaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of TravailleurNationnalite
	 * 
	 */
	default Long count(Request<TravailleurNationnaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from TravailleurNationnalite e where e IS NOT NULL";
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
	default String getWhereExpression(Request<TravailleurNationnaliteDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		TravailleurNationnaliteDto dto = request.getData() != null ? request.getData() : new TravailleurNationnaliteDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (TravailleurNationnaliteDto elt : request.getDatas()) {
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
	default String generateCriteria(TravailleurNationnaliteDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
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
			if (dto.getNationnaliteId()!= null && dto.getNationnaliteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nationnaliteId", dto.getNationnaliteId(), "e.nationnalite.id", "Integer", dto.getNationnaliteIdParam(), param, index, locale));
			}
			if (dto.getTravailleurId()!= null && dto.getTravailleurId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("travailleurId", dto.getTravailleurId(), "e.travailleur.id", "Integer", dto.getTravailleurIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNationnaliteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nationnaliteCode", dto.getNationnaliteCode(), "e.nationnalite.code", "String", dto.getNationnaliteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNationnaliteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nationnaliteLibelle", dto.getNationnaliteLibelle(), "e.nationnalite.libelle", "String", dto.getNationnaliteLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTravailleurNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("travailleurNom", dto.getTravailleurNom(), "e.travailleur.nom", "String", dto.getTravailleurNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTravailleurPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("travailleurPrenom", dto.getTravailleurPrenom(), "e.travailleur.prenom", "String", dto.getTravailleurPrenomParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
