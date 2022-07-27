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
import ci.palmafrique.vonabri.dao.repository.customize._ProductionRepository;

/**
 * Repository : Production.
 */
@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer>, _ProductionRepository {
	/**
	 * Finds Production by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Production whose id is equals to the given id. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.id = :id and e.isDeleted = :isDeleted")
	Production findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Production by using date as a search criteria.
	 * 
	 * @param date
	 * @return An Object Production whose date is equals to the given date. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.date = :date and e.isDeleted = :isDeleted")
	List<Production> findByDate(@Param("date")Date date, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using aciditeHuile as a search criteria.
	 * 
	 * @param aciditeHuile
	 * @return An Object Production whose aciditeHuile is equals to the given aciditeHuile. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.aciditeHuile = :aciditeHuile and e.isDeleted = :isDeleted")
	List<Production> findByAciditeHuile(@Param("aciditeHuile")Double aciditeHuile, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using regimesMalEgra as a search criteria.
	 * 
	 * @param regimesMalEgra
	 * @return An Object Production whose regimesMalEgra is equals to the given regimesMalEgra. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.regimesMalEgra = :regimesMalEgra and e.isDeleted = :isDeleted")
	List<Production> findByRegimesMalEgra(@Param("regimesMalEgra")Double regimesMalEgra, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using cagesCarreau as a search criteria.
	 * 
	 * @param cagesCarreau
	 * @return An Object Production whose cagesCarreau is equals to the given cagesCarreau. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.cagesCarreau = :cagesCarreau and e.isDeleted = :isDeleted")
	List<Production> findByCagesCarreau(@Param("cagesCarreau")String cagesCarreau, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using pi as a search criteria.
	 * 
	 * @param pi
	 * @return An Object Production whose pi is equals to the given pi. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.pi = :pi and e.isDeleted = :isDeleted")
	List<Production> findByPi(@Param("pi")Double pi, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using pvp as a search criteria.
	 * 
	 * @param pvp
	 * @return An Object Production whose pvp is equals to the given pvp. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.pvp = :pvp and e.isDeleted = :isDeleted")
	List<Production> findByPvp(@Param("pvp")Double pvp, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using sortieHuile as a search criteria.
	 * 
	 * @param sortieHuile
	 * @return An Object Production whose sortieHuile is equals to the given sortieHuile. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.sortieHuile = :sortieHuile and e.isDeleted = :isDeleted")
	List<Production> findBySortieHuile(@Param("sortieHuile")Double sortieHuile, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using sortiePalmiste as a search criteria.
	 * 
	 * @param sortiePalmiste
	 * @return An Object Production whose sortiePalmiste is equals to the given sortiePalmiste. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.sortiePalmiste = :sortiePalmiste and e.isDeleted = :isDeleted")
	List<Production> findBySortiePalmiste(@Param("sortiePalmiste")Double sortiePalmiste, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using stokPalmiste as a search criteria.
	 * 
	 * @param stokPalmiste
	 * @return An Object Production whose stokPalmiste is equals to the given stokPalmiste. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.stokPalmiste = :stokPalmiste and e.isDeleted = :isDeleted")
	List<Production> findByStokPalmiste(@Param("stokPalmiste")Double stokPalmiste, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using regimesTraiter as a search criteria.
	 * 
	 * @param regimesTraiter
	 * @return An Object Production whose regimesTraiter is equals to the given regimesTraiter. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.regimesTraiter = :regimesTraiter and e.isDeleted = :isDeleted")
	List<Production> findByRegimesTraiter(@Param("regimesTraiter")Double regimesTraiter, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using productionHuile as a search criteria.
	 * 
	 * @param productionHuile
	 * @return An Object Production whose productionHuile is equals to the given productionHuile. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.productionHuile = :productionHuile and e.isDeleted = :isDeleted")
	List<Production> findByProductionHuile(@Param("productionHuile")Double productionHuile, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using productionPalmiste as a search criteria.
	 * 
	 * @param productionPalmiste
	 * @return An Object Production whose productionPalmiste is equals to the given productionPalmiste. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.productionPalmiste = :productionPalmiste and e.isDeleted = :isDeleted")
	List<Production> findByProductionPalmiste(@Param("productionPalmiste")Double productionPalmiste, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using tauxExtractionHuile as a search criteria.
	 * 
	 * @param tauxExtractionHuile
	 * @return An Object Production whose tauxExtractionHuile is equals to the given tauxExtractionHuile. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.tauxExtractionHuile = :tauxExtractionHuile and e.isDeleted = :isDeleted")
	List<Production> findByTauxExtractionHuile(@Param("tauxExtractionHuile")Double tauxExtractionHuile, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using tauxExtractionPalmiste as a search criteria.
	 * 
	 * @param tauxExtractionPalmiste
	 * @return An Object Production whose tauxExtractionPalmiste is equals to the given tauxExtractionPalmiste. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.tauxExtractionPalmiste = :tauxExtractionPalmiste and e.isDeleted = :isDeleted")
	List<Production> findByTauxExtractionPalmiste(@Param("tauxExtractionPalmiste")Double tauxExtractionPalmiste, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using sortieHuileDeSarci as a search criteria.
	 * 
	 * @param sortieHuileDeSarci
	 * @return An Object Production whose sortieHuileDeSarci is equals to the given sortieHuileDeSarci. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.sortieHuileDeSarci = :sortieHuileDeSarci and e.isDeleted = :isDeleted")
	List<Production> findBySortieHuileDeSarci(@Param("sortieHuileDeSarci")Double sortieHuileDeSarci, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Production whose createdAt is equals to the given createdAt. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Production> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Production whose deletedAt is equals to the given deletedAt. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Production> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Production whose updatedAt is equals to the given updatedAt. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Production> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Production whose createdBy is equals to the given createdBy. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Production> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Production whose updatedBy is equals to the given updatedBy. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Production> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Production whose deletedBy is equals to the given deletedBy. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Production> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Production by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Production whose isDeleted is equals to the given isDeleted. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.isDeleted = :isDeleted")
	List<Production> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Production by using siteId as a search criteria.
	 * 
	 * @param siteId
	 * @return An Object Production whose siteId is equals to the given siteId. If
	 *         no Production is found, this method returns null.
	 */
	@Query("select e from Production e where e.site.id = :siteId and e.isDeleted = :isDeleted")
	List<Production> findBySiteId(@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Production by using productionDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Production
	 * @throws DataAccessException,ParseException
	 */
	default List<Production> getByCriteria(Request<ProductionDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Production e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Production> query = em.createQuery(req, Production.class);
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
	 * Finds count of Production by using productionDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Production
	 * 
	 */
	default Long count(Request<ProductionDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Production e where e IS NOT NULL";
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
	default String getWhereExpression(Request<ProductionDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		ProductionDto dto = request.getData() != null ? request.getData() : new ProductionDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (ProductionDto elt : request.getDatas()) {
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
	default String generateCriteria(ProductionDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("date", dto.getDate(), "e.date", "Date", dto.getDateParam(), param, index, locale));
			}
			if (dto.getAciditeHuile()!= null && dto.getAciditeHuile() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("aciditeHuile", dto.getAciditeHuile(), "e.aciditeHuile", "Double", dto.getAciditeHuileParam(), param, index, locale));
			}
			if (dto.getRegimesMalEgra()!= null && dto.getRegimesMalEgra() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("regimesMalEgra", dto.getRegimesMalEgra(), "e.regimesMalEgra", "Double", dto.getRegimesMalEgraParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCagesCarreau())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("cagesCarreau", dto.getCagesCarreau(), "e.cagesCarreau", "String", dto.getCagesCarreauParam(), param, index, locale));
			}
			if (dto.getPi()!= null && dto.getPi() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("pi", dto.getPi(), "e.pi", "Double", dto.getPiParam(), param, index, locale));
			}
			if (dto.getPvp()!= null && dto.getPvp() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("pvp", dto.getPvp(), "e.pvp", "Double", dto.getPvpParam(), param, index, locale));
			}
			if (dto.getSortieHuile()!= null && dto.getSortieHuile() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sortieHuile", dto.getSortieHuile(), "e.sortieHuile", "Double", dto.getSortieHuileParam(), param, index, locale));
			}
			if (dto.getSortiePalmiste()!= null && dto.getSortiePalmiste() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sortiePalmiste", dto.getSortiePalmiste(), "e.sortiePalmiste", "Double", dto.getSortiePalmisteParam(), param, index, locale));
			}
			if (dto.getStokPalmiste()!= null && dto.getStokPalmiste() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("stokPalmiste", dto.getStokPalmiste(), "e.stokPalmiste", "Double", dto.getStokPalmisteParam(), param, index, locale));
			}
			if (dto.getRegimesTraiter()!= null && dto.getRegimesTraiter() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("regimesTraiter", dto.getRegimesTraiter(), "e.regimesTraiter", "Double", dto.getRegimesTraiterParam(), param, index, locale));
			}
			if (dto.getProductionHuile()!= null && dto.getProductionHuile() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("productionHuile", dto.getProductionHuile(), "e.productionHuile", "Double", dto.getProductionHuileParam(), param, index, locale));
			}
			if (dto.getProductionPalmiste()!= null && dto.getProductionPalmiste() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("productionPalmiste", dto.getProductionPalmiste(), "e.productionPalmiste", "Double", dto.getProductionPalmisteParam(), param, index, locale));
			}
			if (dto.getTauxExtractionHuile()!= null && dto.getTauxExtractionHuile() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tauxExtractionHuile", dto.getTauxExtractionHuile(), "e.tauxExtractionHuile", "Double", dto.getTauxExtractionHuileParam(), param, index, locale));
			}
			if (dto.getTauxExtractionPalmiste()!= null && dto.getTauxExtractionPalmiste() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tauxExtractionPalmiste", dto.getTauxExtractionPalmiste(), "e.tauxExtractionPalmiste", "Double", dto.getTauxExtractionPalmisteParam(), param, index, locale));
			}
			if (dto.getSortieHuileDeSarci()!= null && dto.getSortieHuileDeSarci() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sortieHuileDeSarci", dto.getSortieHuileDeSarci(), "e.sortieHuileDeSarci", "Double", dto.getSortieHuileDeSarciParam(), param, index, locale));
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
			if (dto.getSiteId()!= null && dto.getSiteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteId", dto.getSiteId(), "e.site.id", "Integer", dto.getSiteIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteCode", dto.getSiteCode(), "e.site.code", "String", dto.getSiteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteLibelle", dto.getSiteLibelle(), "e.site.libelle", "String", dto.getSiteLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
