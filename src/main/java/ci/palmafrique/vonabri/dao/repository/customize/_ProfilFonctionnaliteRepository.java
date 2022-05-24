package ci.palmafrique.vonabri.dao.repository.customize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.palmafrique.vonabri.dao.entity.Fonctionnalite;
import ci.palmafrique.vonabri.dao.entity.ProfilFonctionnalite;
import ci.palmafrique.vonabri.utils.dto.ProfilFonctionnaliteDto;

/**
 * Repository customize : ProfilFonctionnalite.
 */
@Repository
public interface _ProfilFonctionnaliteRepository {
	default List<String> _generateCriteria(ProfilFonctionnaliteDto dto, HashMap<String, java.lang.Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}
	
	@Query("select e from ProfilFonctionnalite e where e.profil.id = :profilId and e.fonctionnalite.id = :fonctionnaliteId and e.isDeleted = :isDeleted")
	ProfilFonctionnalite findByProfilIdAndFonctionnaliteId(@Param("profilId")Integer profilId, @Param("fonctionnaliteId")Integer fonctionnaliteId, @Param("isDeleted")Boolean isDeleted);

	@Query("select e.fonctionnalite from ProfilFonctionnalite e where e.profil.id = :profilId and e.isDeleted = :isDeleted")
	List<Fonctionnalite> findFonctionnaliteByProfilId(@Param("profilId")Integer profilId, @Param("isDeleted")Boolean isDeleted);

}
