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
import ci.palmafrique.vonabri.dao.repository.customize._StockHuileRepository;

/**
 * Repository : StockHuile.
 */
@Repository
public interface StockHuileRepository extends JpaRepository<StockHuile, Integer>, _StockHuileRepository {
	/**
	 * Finds StockHuile by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object StockHuile whose id is equals to the given id. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.id = :id and e.isDeleted = :isDeleted")
	StockHuile findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds StockHuile by using stock as a search criteria.
	 * 
	 * @param stock
	 * @return An Object StockHuile whose stock is equals to the given stock. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.stock = :stock and e.isDeleted = :isDeleted")
	List<StockHuile> findByStock(@Param("stock")Double stock, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds StockHuile by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object StockHuile whose createdAt is equals to the given createdAt. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<StockHuile> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds StockHuile by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object StockHuile whose deletedAt is equals to the given deletedAt. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<StockHuile> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds StockHuile by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object StockHuile whose updatedAt is equals to the given updatedAt. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<StockHuile> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds StockHuile by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object StockHuile whose createdBy is equals to the given createdBy. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<StockHuile> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds StockHuile by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object StockHuile whose updatedBy is equals to the given updatedBy. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<StockHuile> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds StockHuile by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object StockHuile whose deletedBy is equals to the given deletedBy. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<StockHuile> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds StockHuile by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object StockHuile whose isDeleted is equals to the given isDeleted. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.isDeleted = :isDeleted")
	List<StockHuile> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds StockHuile by using productionId as a search criteria.
	 * 
	 * @param productionId
	 * @return An Object StockHuile whose productionId is equals to the given productionId. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.production.id = :productionId and e.isDeleted = :isDeleted")
	List<StockHuile> findByProductionId(@Param("productionId")Integer productionId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds StockHuile by using tankId as a search criteria.
	 * 
	 * @param tankId
	 * @return An Object StockHuile whose tankId is equals to the given tankId. If
	 *         no StockHuile is found, this method returns null.
	 */
	@Query("select e from StockHuile e where e.tank.id = :tankId and e.isDeleted = :isDeleted")
	List<StockHuile> findByTankId(@Param("tankId")Integer tankId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of StockHuile by using stockHuileDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of StockHuile
	 * @throws DataAccessException,ParseException
	 */
	default List<StockHuile> getByCriteria(Request<StockHuileDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from StockHuile e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<StockHuile> query = em.createQuery(req, StockHuile.class);
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
	 * Finds count of StockHuile by using stockHuileDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of StockHuile
	 * 
	 */
	default Long count(Request<StockHuileDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from StockHuile e where e IS NOT NULL";
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
	default String getWhereExpression(Request<StockHuileDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		StockHuileDto dto = request.getData() != null ? request.getData() : new StockHuileDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (StockHuileDto elt : request.getDatas()) {
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
	default String generateCriteria(StockHuileDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getStock()!= null && dto.getStock() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("stock", dto.getStock(), "e.stock", "Double", dto.getStockParam(), param, index, locale));
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
			if (dto.getTankId()!= null && dto.getTankId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tankId", dto.getTankId(), "e.tank.id", "Integer", dto.getTankIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTankCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tankCode", dto.getTankCode(), "e.tank.code", "String", dto.getTankCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTankLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tankLibelle", dto.getTankLibelle(), "e.tank.libelle", "String", dto.getTankLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
