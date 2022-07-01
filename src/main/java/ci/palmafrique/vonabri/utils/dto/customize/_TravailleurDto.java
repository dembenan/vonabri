
/*
 * Java dto for entity table travailleur 
 * Created on 2022-06-11 ( Time 19:24:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.palmafrique.vonabri.utils.dto.NationnaliteDto;
import ci.palmafrique.vonabri.utils.dto.UserDto;
import lombok.Data;

/**
 * DTO customize for table "travailleur"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _TravailleurDto {

	private Boolean isUser;
    private Integer    profilId  ;
    private Integer    userTypeId  ;
	protected Boolean 		isSuperAdmin;
	private List<NationnaliteDto> nationnalites;
	private UserDto userInfo ;
	private String profilLibelle;
	private String userTypeLibelle;

}
