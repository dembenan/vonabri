
/*
 * Java dto for entity table travailleur_nationnalite 
 * Created on 2022-06-11 ( Time 19:24:50 )
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
import ci.palmafrique.vonabri.utils.dto.customize._TravailleurNationnaliteDto;

/**
 * DTO for table "travailleur_nationnalite"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class TravailleurNationnaliteDto extends _TravailleurNationnaliteDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    travailleurId        ;
    private Integer    nationnaliteId       ;
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
	private String nationnaliteCode;
	private String nationnaliteLibelle;
	private String travailleurNom;
	private String travailleurPrenom;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  travailleurIdParam    ;                     
	private SearchParam<Integer>  nationnaliteIdParam   ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   nationnaliteCodeParam ;                     
	private SearchParam<String>   nationnaliteLibelleParam;                     
	private SearchParam<String>   travailleurNomParam   ;                     
	private SearchParam<String>   travailleurPrenomParam;                     
    /**
     * Default constructor
     */
    public TravailleurNationnaliteDto()
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
