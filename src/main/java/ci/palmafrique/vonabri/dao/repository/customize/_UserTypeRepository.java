package ci.palmafrique.vonabri.dao.repository.customize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.palmafrique.vonabri.dao.entity.User;
import ci.palmafrique.vonabri.utils.dto.UserTypeDto;

/**
 * Repository customize : UserType.
 */
@Repository
public interface _UserTypeRepository {
	default List<String> _generateCriteria(UserTypeDto dto, HashMap<String, java.lang.Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}

}
