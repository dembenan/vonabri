
/*
 * Java dto for entity table anciennete 
 * Created on 2022-06-11 ( Time 19:24:46 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.palmafrique.vonabri.utils.contract.*;
import ci.palmafrique.vonabri.utils.dto.customize._AncienneteDto;

/**
 * DTO for table "anciennete"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class AncienneteDto extends _AncienneteDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     code                 ;
    private String     libelle              ;
    private Integer    ancienneteTypeId     ;
	private String     createdAt            ;
	private String     deletedAt            ;
	private String     updatedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String ancienneteTypeCode;
	private String ancienneteTypeLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<Integer>  ancienneteTypeIdParam ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   ancienneteTypeCodeParam;                     
	private SearchParam<String>   ancienneteTypeLibelleParam;                     
    /**
     * Default constructor
     */
    public AncienneteDto()
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
