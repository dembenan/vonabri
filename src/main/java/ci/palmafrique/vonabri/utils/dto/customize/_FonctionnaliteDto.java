
/*
 * Java dto for entity table fonctionnalite 
 * Created on 2022-04-25 ( Time 03:17:01 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.palmafrique.vonabri.utils.dto.FonctionnaliteDto;
import lombok.Data;

/**
 * DTO customize for table "fonctionnalite"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _FonctionnaliteDto {
	private List<FonctionnaliteDto> datasChildren;
	private String name;
	private String parentCode;

}
