
/*
 * Java dto for entity table commentaire 
 * Created on 2022-07-27 ( Time 01:05:40 )
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
import ci.palmafrique.vonabri.utils.dto.customize._CommentaireDto;

/**
 * DTO for table "commentaire"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class CommentaireDto extends _CommentaireDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    productionId         ;
    private Integer    commentaireTypeId    ;
    private String     description          ;
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
	private String commentaireTypeCode;
	private String commentaireTypeLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  productionIdParam     ;                     
	private SearchParam<Integer>  commentaireTypeIdParam;                     
	private SearchParam<Byte[]>   descriptionParam      ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   commentaireTypeCodeParam;                     
	private SearchParam<String>   commentaireTypeLibelleParam;                     
    /**
     * Default constructor
     */
    public CommentaireDto()
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
