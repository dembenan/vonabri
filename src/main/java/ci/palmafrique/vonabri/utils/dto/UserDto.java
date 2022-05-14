
/*
 * Java dto for entity table user 
 * Created on 2022-04-25 ( Time 03:17:04 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.palmafrique.vonabri.utils.contract.*;
import ci.palmafrique.vonabri.utils.dto.customize._UserDto;

/**
 * DTO for table "user"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class UserDto extends _UserDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     email                ;
    private String     password             ;
    private Integer    profilId             ;
    private Integer    userTypeId           ;
    private Boolean    isLocked             ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;
    private Boolean    isConnected          ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String userTypeCode;
	private String userTypeLibelle;
	private String profilCode;
	private String profilLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   emailParam            ;                     
	private SearchParam<String>   passwordParam         ;                     
	private SearchParam<Integer>  profilIdParam         ;                     
	private SearchParam<Integer>  userTypeIdParam       ;                     
	private SearchParam<Boolean>  isLockedParam         ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Boolean>  isConnectedParam      ;                     
	private SearchParam<String>   userTypeCodeParam     ;                     
	private SearchParam<String>   userTypeLibelleParam  ;                     
	private SearchParam<String>   profilCodeParam       ;                     
	private SearchParam<String>   profilLibelleParam    ;                     
    /**
     * Default constructor
     */
    public UserDto()
    {
        super();
    }
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
