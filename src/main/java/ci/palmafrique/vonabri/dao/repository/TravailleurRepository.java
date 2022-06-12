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
import ci.palmafrique.vonabri.dao.repository.customize._TravailleurRepository;

/**
 * Repository : Travailleur.
 */
@Repository
public interface TravailleurRepository extends JpaRepository<Travailleur, Integer>, _TravailleurRepository {
	/**
	 * Finds Travailleur by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Travailleur whose id is equals to the given id. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.id = :id and e.isDeleted = :isDeleted")
	Travailleur findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Travailleur whose nom is equals to the given nom. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Travailleur> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using prenom as a search criteria.
	 * 
	 * @param prenom
	 * @return An Object Travailleur whose prenom is equals to the given prenom. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.prenom = :prenom and e.isDeleted = :isDeleted")
	List<Travailleur> findByPrenom(@Param("prenom")String prenom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using email as a search criteria.
	 * 
	 * @param email
	 * @return An Object Travailleur whose email is equals to the given email. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.email = :email and e.isDeleted = :isDeleted")
	Travailleur findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using contact1 as a search criteria.
	 * 
	 * @param contact1
	 * @return An Object Travailleur whose contact1 is equals to the given contact1. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.contact1 = :contact1 and e.isDeleted = :isDeleted")
	List<Travailleur> findByContact1(@Param("contact1")String contact1, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using contact2 as a search criteria.
	 * 
	 * @param contact2
	 * @return An Object Travailleur whose contact2 is equals to the given contact2. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.contact2 = :contact2 and e.isDeleted = :isDeleted")
	List<Travailleur> findByContact2(@Param("contact2")String contact2, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using domicile as a search criteria.
	 * 
	 * @param domicile
	 * @return An Object Travailleur whose domicile is equals to the given domicile. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.domicile = :domicile and e.isDeleted = :isDeleted")
	List<Travailleur> findByDomicile(@Param("domicile")String domicile, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using dateEmbauche as a search criteria.
	 * 
	 * @param dateEmbauche
	 * @return An Object Travailleur whose dateEmbauche is equals to the given dateEmbauche. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.dateEmbauche = :dateEmbauche and e.isDeleted = :isDeleted")
	List<Travailleur> findByDateEmbauche(@Param("dateEmbauche")Date dateEmbauche, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using matricule as a search criteria.
	 * 
	 * @param matricule
	 * @return An Object Travailleur whose matricule is equals to the given matricule. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.matricule = :matricule and e.isDeleted = :isDeleted")
	Travailleur findByMatricule(@Param("matricule")String matricule, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using nombreEpouse as a search criteria.
	 * 
	 * @param nombreEpouse
	 * @return An Object Travailleur whose nombreEpouse is equals to the given nombreEpouse. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nombreEpouse = :nombreEpouse and e.isDeleted = :isDeleted")
	List<Travailleur> findByNombreEpouse(@Param("nombreEpouse")Integer nombreEpouse, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using nombreEnfants as a search criteria.
	 * 
	 * @param nombreEnfants
	 * @return An Object Travailleur whose nombreEnfants is equals to the given nombreEnfants. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nombreEnfants = :nombreEnfants and e.isDeleted = :isDeleted")
	List<Travailleur> findByNombreEnfants(@Param("nombreEnfants")Integer nombreEnfants, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using nombreFilles as a search criteria.
	 * 
	 * @param nombreFilles
	 * @return An Object Travailleur whose nombreFilles is equals to the given nombreFilles. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nombreFilles = :nombreFilles and e.isDeleted = :isDeleted")
	List<Travailleur> findByNombreFilles(@Param("nombreFilles")Integer nombreFilles, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using nombreGarcons as a search criteria.
	 * 
	 * @param nombreGarcons
	 * @return An Object Travailleur whose nombreGarcons is equals to the given nombreGarcons. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nombreGarcons = :nombreGarcons and e.isDeleted = :isDeleted")
	List<Travailleur> findByNombreGarcons(@Param("nombreGarcons")Integer nombreGarcons, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using nombreAdoption as a search criteria.
	 * 
	 * @param nombreAdoption
	 * @return An Object Travailleur whose nombreAdoption is equals to the given nombreAdoption. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nombreAdoption = :nombreAdoption and e.isDeleted = :isDeleted")
	List<Travailleur> findByNombreAdoption(@Param("nombreAdoption")Integer nombreAdoption, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using nombreEnfantSousTutelleDirect as a search criteria.
	 * 
	 * @param nombreEnfantSousTutelleDirect
	 * @return An Object Travailleur whose nombreEnfantSousTutelleDirect is equals to the given nombreEnfantSousTutelleDirect. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nombreEnfantSousTutelleDirect = :nombreEnfantSousTutelleDirect and e.isDeleted = :isDeleted")
	List<Travailleur> findByNombreEnfantSousTutelleDirect(@Param("nombreEnfantSousTutelleDirect")Integer nombreEnfantSousTutelleDirect, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using nombreEnfantSousTutelleIndirect as a search criteria.
	 * 
	 * @param nombreEnfantSousTutelleIndirect
	 * @return An Object Travailleur whose nombreEnfantSousTutelleIndirect is equals to the given nombreEnfantSousTutelleIndirect. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.nombreEnfantSousTutelleIndirect = :nombreEnfantSousTutelleIndirect and e.isDeleted = :isDeleted")
	List<Travailleur> findByNombreEnfantSousTutelleIndirect(@Param("nombreEnfantSousTutelleIndirect")Integer nombreEnfantSousTutelleIndirect, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Travailleur whose createdAt is equals to the given createdAt. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Travailleur> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Travailleur whose deletedAt is equals to the given deletedAt. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Travailleur> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Travailleur whose updatedAt is equals to the given updatedAt. If
	 *         no Travailleur is found, this method returns null.
	 */
	
	@Query("select e from Travailleur e where e.dateDeNaissance = :dateDeNaissance and e.isDeleted = :isDeleted")
	List<Travailleur> findByDateDenaissance(@Param("dateDeNaissance")Date dateDeNaissance, @Param("isDeleted")Boolean isDeleted);

	
	
	@Query("select e from Travailleur e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Travailleur> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Travailleur whose createdBy is equals to the given createdBy. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Travailleur> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Travailleur whose updatedBy is equals to the given updatedBy. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Travailleur> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Travailleur whose deletedBy is equals to the given deletedBy. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Travailleur> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Travailleur by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Travailleur whose isDeleted is equals to the given isDeleted. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.isDeleted = :isDeleted")
	List<Travailleur> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using sousPosteDeTravailId as a search criteria.
	 * 
	 * @param sousPosteDeTravailId
	 * @return An Object Travailleur whose sousPosteDeTravailId is equals to the given sousPosteDeTravailId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.sousPosteDeTravail.id = :sousPosteDeTravailId and e.isDeleted = :isDeleted")
	List<Travailleur> findBySousPosteDeTravailId(@Param("sousPosteDeTravailId")Integer sousPosteDeTravailId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using employeurId as a search criteria.
	 * 
	 * @param employeurId
	 * @return An Object Travailleur whose employeurId is equals to the given employeurId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.employeur.id = :employeurId and e.isDeleted = :isDeleted")
	List<Travailleur> findByEmployeurId(@Param("employeurId")Integer employeurId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using siteId as a search criteria.
	 * 
	 * @param siteId
	 * @return An Object Travailleur whose siteId is equals to the given siteId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.site.id = :siteId and e.isDeleted = :isDeleted")
	List<Travailleur> findBySiteId(@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using ancienneteSocieteId as a search criteria.
	 * 
	 * @param ancienneteSocieteId
	 * @return An Object Travailleur whose ancienneteSocieteId is equals to the given ancienneteSocieteId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.ancienneteSociete.id = :ancienneteSocieteId and e.isDeleted = :isDeleted")
	List<Travailleur> findByAncienneteSocieteId(@Param("ancienneteSocieteId")Integer ancienneteSocieteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using regimeId as a search criteria.
	 * 
	 * @param regimeId
	 * @return An Object Travailleur whose regimeId is equals to the given regimeId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.regime.id = :regimeId and e.isDeleted = :isDeleted")
	List<Travailleur> findByRegimeId(@Param("regimeId")Integer regimeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using anciennetePosteId as a search criteria.
	 * 
	 * @param anciennetePosteId
	 * @return An Object Travailleur whose anciennetePosteId is equals to the given anciennetePosteId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.anciennetePoste.id = :anciennetePosteId and e.isDeleted = :isDeleted")
	List<Travailleur> findByAnciennetePosteId(@Param("anciennetePosteId")Integer anciennetePosteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using ethniePereId as a search criteria.
	 * 
	 * @param ethniePereId
	 * @return An Object Travailleur whose ethniePereId is equals to the given ethniePereId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.ethniePere.id = :ethniePereId and e.isDeleted = :isDeleted")
	List<Travailleur> findByEthniePereId(@Param("ethniePereId")Integer ethniePereId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using statutId as a search criteria.
	 * 
	 * @param statutId
	 * @return An Object Travailleur whose statutId is equals to the given statutId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.statut.id = :statutId and e.isDeleted = :isDeleted")
	List<Travailleur> findByStatutId(@Param("statutId")Integer statutId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using ethnieMereId as a search criteria.
	 * 
	 * @param ethnieMereId
	 * @return An Object Travailleur whose ethnieMereId is equals to the given ethnieMereId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.ethnieMere.id = :ethnieMereId and e.isDeleted = :isDeleted")
	List<Travailleur> findByEthnieMereId(@Param("ethnieMereId")Integer ethnieMereId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using gestionDeBienId as a search criteria.
	 * 
	 * @param gestionDeBienId
	 * @return An Object Travailleur whose gestionDeBienId is equals to the given gestionDeBienId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.gestionDeBien.id = :gestionDeBienId and e.isDeleted = :isDeleted")
	List<Travailleur> findByGestionDeBienId(@Param("gestionDeBienId")Integer gestionDeBienId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using posteDeTravailId as a search criteria.
	 * 
	 * @param posteDeTravailId
	 * @return An Object Travailleur whose posteDeTravailId is equals to the given posteDeTravailId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.posteDeTravail.id = :posteDeTravailId and e.isDeleted = :isDeleted")
	List<Travailleur> findByPosteDeTravailId(@Param("posteDeTravailId")Integer posteDeTravailId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using fonctionId as a search criteria.
	 * 
	 * @param fonctionId
	 * @return An Object Travailleur whose fonctionId is equals to the given fonctionId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.fonction.id = :fonctionId and e.isDeleted = :isDeleted")
	List<Travailleur> findByFonctionId(@Param("fonctionId")Integer fonctionId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using directionId as a search criteria.
	 * 
	 * @param directionId
	 * @return An Object Travailleur whose directionId is equals to the given directionId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.direction.id = :directionId and e.isDeleted = :isDeleted")
	List<Travailleur> findByDirectionId(@Param("directionId")Integer directionId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using typeMariageId as a search criteria.
	 * 
	 * @param typeMariageId
	 * @return An Object Travailleur whose typeMariageId is equals to the given typeMariageId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.typeMariage.id = :typeMariageId and e.isDeleted = :isDeleted")
	List<Travailleur> findByTypeMariageId(@Param("typeMariageId")Integer typeMariageId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using statutMatrimonialId as a search criteria.
	 * 
	 * @param statutMatrimonialId
	 * @return An Object Travailleur whose statutMatrimonialId is equals to the given statutMatrimonialId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.statutMatrimonial.id = :statutMatrimonialId and e.isDeleted = :isDeleted")
	List<Travailleur> findByStatutMatrimonialId(@Param("statutMatrimonialId")Integer statutMatrimonialId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Travailleur by using sousSiteId as a search criteria.
	 * 
	 * @param sousSiteId
	 * @return An Object Travailleur whose sousSiteId is equals to the given sousSiteId. If
	 *         no Travailleur is found, this method returns null.
	 */
	@Query("select e from Travailleur e where e.sousSite.id = :sousSiteId and e.isDeleted = :isDeleted")
	List<Travailleur> findBySousSiteId(@Param("sousSiteId")Integer sousSiteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Travailleur by using travailleurDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Travailleur
	 * @throws DataAccessException,ParseException
	 */
	default List<Travailleur> getByCriteria(Request<TravailleurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Travailleur e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Travailleur> query = em.createQuery(req, Travailleur.class);
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
	 * Finds count of Travailleur by using travailleurDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Travailleur
	 * 
	 */
	default Long count(Request<TravailleurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Travailleur e where e IS NOT NULL";
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
	default String getWhereExpression(Request<TravailleurDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		TravailleurDto dto = request.getData() != null ? request.getData() : new TravailleurDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (TravailleurDto elt : request.getDatas()) {
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
	default String generateCriteria(TravailleurDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nom", dto.getNom(), "e.nom", "String", dto.getNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("prenom", dto.getPrenom(), "e.prenom", "String", dto.getPrenomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEmail())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getContact1())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("contact1", dto.getContact1(), "e.contact1", "String", dto.getContact1Param(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getContact2())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("contact2", dto.getContact2(), "e.contact2", "String", dto.getContact2Param(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDomicile())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("domicile", dto.getDomicile(), "e.domicile", "String", dto.getDomicileParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDateEmbauche())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateEmbauche", dto.getDateEmbauche(), "e.dateEmbauche", "Date", dto.getDateEmbaucheParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMatricule())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("matricule", dto.getMatricule(), "e.matricule", "String", dto.getMatriculeParam(), param, index, locale));
			}
			if (dto.getNombreEpouse()!= null && dto.getNombreEpouse() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreEpouse", dto.getNombreEpouse(), "e.nombreEpouse", "Integer", dto.getNombreEpouseParam(), param, index, locale));
			}
			if (dto.getNombreEnfants()!= null && dto.getNombreEnfants() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreEnfants", dto.getNombreEnfants(), "e.nombreEnfants", "Integer", dto.getNombreEnfantsParam(), param, index, locale));
			}
			if (dto.getNombreFilles()!= null && dto.getNombreFilles() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreFilles", dto.getNombreFilles(), "e.nombreFilles", "Integer", dto.getNombreFillesParam(), param, index, locale));
			}
			if (dto.getNombreGarcons()!= null && dto.getNombreGarcons() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreGarcons", dto.getNombreGarcons(), "e.nombreGarcons", "Integer", dto.getNombreGarconsParam(), param, index, locale));
			}
			if (dto.getNombreAdoption()!= null && dto.getNombreAdoption() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreAdoption", dto.getNombreAdoption(), "e.nombreAdoption", "Integer", dto.getNombreAdoptionParam(), param, index, locale));
			}
			if (dto.getNombreEnfantSousTutelleDirect()!= null && dto.getNombreEnfantSousTutelleDirect() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreEnfantSousTutelleDirect", dto.getNombreEnfantSousTutelleDirect(), "e.nombreEnfantSousTutelleDirect", "Integer", dto.getNombreEnfantSousTutelleDirectParam(), param, index, locale));
			}
			if (dto.getNombreEnfantSousTutelleIndirect()!= null && dto.getNombreEnfantSousTutelleIndirect() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreEnfantSousTutelleIndirect", dto.getNombreEnfantSousTutelleIndirect(), "e.nombreEnfantSousTutelleIndirect", "Integer", dto.getNombreEnfantSousTutelleIndirectParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDateDeNaissance())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateDeNaissance", dto.getDateDeNaissance(), "e.dateDeNaissance", "Date", dto.getDateDeNaissanceParam(), param, index, locale));
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
			if (dto.getSousPosteDeTravailId()!= null && dto.getSousPosteDeTravailId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sousPosteDeTravailId", dto.getSousPosteDeTravailId(), "e.sousPosteDeTravail.id", "Integer", dto.getSousPosteDeTravailIdParam(), param, index, locale));
			}
			if (dto.getEmployeurId()!= null && dto.getEmployeurId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("employeurId", dto.getEmployeurId(), "e.employeur.id", "Integer", dto.getEmployeurIdParam(), param, index, locale));
			}
			if (dto.getSiteId()!= null && dto.getSiteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteId", dto.getSiteId(), "e.site.id", "Integer", dto.getSiteIdParam(), param, index, locale));
			}
			if (dto.getAncienneteSocieteId()!= null && dto.getAncienneteSocieteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteSocieteId", dto.getAncienneteSocieteId(), "e.anciennete.id", "Integer", dto.getAncienneteSocieteIdParam(), param, index, locale));
			}
			if (dto.getRegimeId()!= null && dto.getRegimeId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("regimeId", dto.getRegimeId(), "e.regime.id", "Integer", dto.getRegimeIdParam(), param, index, locale));
			}
			if (dto.getAnciennetePosteId()!= null && dto.getAnciennetePosteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("anciennetePosteId", dto.getAnciennetePosteId(), "e.anciennete.id", "Integer", dto.getAnciennetePosteIdParam(), param, index, locale));
			}
			if (dto.getEthniePereId()!= null && dto.getEthniePereId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ethniePereId", dto.getEthniePereId(), "e.ethnie.id", "Integer", dto.getEthniePereIdParam(), param, index, locale));
			}
			if (dto.getStatutId()!= null && dto.getStatutId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("statutId", dto.getStatutId(), "e.statut.id", "Integer", dto.getStatutIdParam(), param, index, locale));
			}
			if (dto.getEthnieMereId()!= null && dto.getEthnieMereId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ethnieMereId", dto.getEthnieMereId(), "e.ethnie.id", "Integer", dto.getEthnieMereIdParam(), param, index, locale));
			}
			if (dto.getGestionDeBienId()!= null && dto.getGestionDeBienId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("gestionDeBienId", dto.getGestionDeBienId(), "e.gestionDeBien.id", "Integer", dto.getGestionDeBienIdParam(), param, index, locale));
			}
			if (dto.getPosteDeTravailId()!= null && dto.getPosteDeTravailId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("posteDeTravailId", dto.getPosteDeTravailId(), "e.posteDeTravail.id", "Integer", dto.getPosteDeTravailIdParam(), param, index, locale));
			}
			if (dto.getFonctionId()!= null && dto.getFonctionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionId", dto.getFonctionId(), "e.fonction.id", "Integer", dto.getFonctionIdParam(), param, index, locale));
			}
			if (dto.getDirectionId()!= null && dto.getDirectionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("directionId", dto.getDirectionId(), "e.direction.id", "Integer", dto.getDirectionIdParam(), param, index, locale));
			}
			if (dto.getTypeMariageId()!= null && dto.getTypeMariageId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeMariageId", dto.getTypeMariageId(), "e.typeMariage.id", "Integer", dto.getTypeMariageIdParam(), param, index, locale));
			}
			if (dto.getStatutMatrimonialId()!= null && dto.getStatutMatrimonialId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("statutMatrimonialId", dto.getStatutMatrimonialId(), "e.statutMatrimonial.id", "Integer", dto.getStatutMatrimonialIdParam(), param, index, locale));
			}
			if (dto.getSousSiteId()!= null && dto.getSousSiteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sousSiteId", dto.getSousSiteId(), "e.sousSite.id", "Integer", dto.getSousSiteIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSousPosteDeTravailCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sousPosteDeTravailCode", dto.getSousPosteDeTravailCode(), "e.sousPosteDeTravail.code", "String", dto.getSousPosteDeTravailCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSousPosteDeTravailLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sousPosteDeTravailLibelle", dto.getSousPosteDeTravailLibelle(), "e.sousPosteDeTravail.libelle", "String", dto.getSousPosteDeTravailLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEmployeurCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("employeurCode", dto.getEmployeurCode(), "e.employeur.code", "String", dto.getEmployeurCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEmployeurLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("employeurLibelle", dto.getEmployeurLibelle(), "e.employeur.libelle", "String", dto.getEmployeurLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteCode", dto.getSiteCode(), "e.site.code", "String", dto.getSiteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteLibelle", dto.getSiteLibelle(), "e.site.libelle", "String", dto.getSiteLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAncienneteSocieteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteCode", dto.getAncienneteSocieteCode(), "e.anciennete.code", "String", dto.getAncienneteSocieteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAncienneteSocieteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteLibelle", dto.getAncienneteSocieteLibelle(), "e.anciennete.libelle", "String", dto.getAncienneteSocieteLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRegimeCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("regimeCode", dto.getRegimeCode(), "e.regime.code", "String", dto.getRegimeCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRegimeLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("regimeLibelle", dto.getRegimeLibelle(), "e.regime.libelle", "String", dto.getRegimeLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAnciennetePosteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteCode", dto.getAnciennetePosteCode(), "e.anciennete.code", "String", dto.getAnciennetePosteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAnciennetePosteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ancienneteLibelle", dto.getAnciennetePosteLibelle(), "e.anciennete.libelle", "String", dto.getAnciennetePosteLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEthniePereCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ethnieCode", dto.getEthniePereCode(), "e.ethnie2.code", "String", dto.getEthniePereCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEthniePereLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ethnieLibelle", dto.getEthniePereLibelle(), "e.ethnie2.libelle", "String", dto.getEthniePereLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatutCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("statutCode", dto.getStatutCode(), "e.statut.code", "String", dto.getStatutCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatutLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("statutLibelle", dto.getStatutLibelle(), "e.statut.libelle", "String", dto.getStatutLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEthnieMereCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ethnieCode", dto.getEthnieMereCode(), "e.ethnie.code", "String", dto.getEthnieMereCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEthnieMereLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ethnieLibelle", dto.getEthnieMereLibelle(), "e.ethnie.libelle", "String", dto.getEthnieMereLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getGestionDeBienCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("gestionDeBienCode", dto.getGestionDeBienCode(), "e.gestionDeBien.code", "String", dto.getGestionDeBienCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getGestionDeBienLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("gestionDeBienLibelle", dto.getGestionDeBienLibelle(), "e.gestionDeBien.libelle", "String", dto.getGestionDeBienLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPosteDeTravailCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("posteDeTravailCode", dto.getPosteDeTravailCode(), "e.posteDeTravail.code", "String", dto.getPosteDeTravailCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPosteDeTravailLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("posteDeTravailLibelle", dto.getPosteDeTravailLibelle(), "e.posteDeTravail.libelle", "String", dto.getPosteDeTravailLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFonctionCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionCode", dto.getFonctionCode(), "e.fonction.code", "String", dto.getFonctionCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFonctionLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionLibelle", dto.getFonctionLibelle(), "e.fonction.libelle", "String", dto.getFonctionLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDirectionCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("directionCode", dto.getDirectionCode(), "e.direction.code", "String", dto.getDirectionCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDirectionLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("directionLibelle", dto.getDirectionLibelle(), "e.direction.libelle", "String", dto.getDirectionLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeMariageCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeMariageCode", dto.getTypeMariageCode(), "e.typeMariage.code", "String", dto.getTypeMariageCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeMariageLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeMariageLibelle", dto.getTypeMariageLibelle(), "e.typeMariage.libelle", "String", dto.getTypeMariageLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatutMatrimonialCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("statutMatrimonialCode", dto.getStatutMatrimonialCode(), "e.statutMatrimonial.code", "String", dto.getStatutMatrimonialCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatutMatrimonialLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("statutMatrimonialLibelle", dto.getStatutMatrimonialLibelle(), "e.statutMatrimonial.libelle", "String", dto.getStatutMatrimonialLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSousSiteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sousSiteCode", dto.getSousSiteCode(), "e.sousSite.code", "String", dto.getSousSiteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSousSiteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sousSiteLibelle", dto.getSousSiteLibelle(), "e.sousSite.libelle", "String", dto.getSousSiteLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
