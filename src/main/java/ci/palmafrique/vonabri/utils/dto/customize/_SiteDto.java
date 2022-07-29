
/*
 * Java dto for entity table site 
 * Created on 2022-06-11 ( Time 19:24:48 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.palmafrique.vonabri.utils.dto.LivraisonDto;
import lombok.Data;

/**
 * DTO customize for table "site"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _SiteDto {

	private List<LivraisonDto> livraisons;
}
