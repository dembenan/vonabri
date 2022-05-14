
/*
 * Java dto for entity table element 
 * Created on 2022-05-05 ( Time 15:22:34 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.palmafrique.vonabri.utils.dto.ElementDto;
import lombok.Data;

/**
 * DTO customize for table "element"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _ElementDto {
	private List<ElementDto> datasChildren;

}
