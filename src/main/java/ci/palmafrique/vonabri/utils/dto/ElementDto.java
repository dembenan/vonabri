
/*
 * Java dto for entity table element 
 * Created on 2022-05-05 ( Time 15:22:34 )
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
import ci.palmafrique.vonabri.utils.dto.customize._ElementDto;

/**
 * DTO for table "element"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ElementDto extends _ElementDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     code                 ;
    private String     libelle              ;
    private String     icon                 ;
    private Integer    parentId             ;
    private Integer    elementTypeId        ;
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
	private String elementTypeCode;
	private String elementTypeLibelle;
	private String elementCode;
	private String elementLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<String>   iconParam             ;                     
	private SearchParam<Integer>  parentIdParam         ;                     
	private SearchParam<Integer>  elementTypeIdParam    ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   elementTypeCodeParam  ;                     
	private SearchParam<String>   elementTypeLibelleParam;                     
	private SearchParam<String>   elementCodeParam      ;                     
	private SearchParam<String>   elementLibelleParam   ;                     
    /**
     * Default constructor
     */
    public ElementDto()
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
