
/*
 * Java dto for entity table livraison 
 * Created on 2022-07-27 ( Time 01:05:42 )
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
import ci.palmafrique.vonabri.utils.dto.customize._LivraisonDto;

/**
 * DTO for table "livraison"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class LivraisonDto extends _LivraisonDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    livraisonTypeId      ;
    private Integer    siteId               ;
    private Integer    productionId         ;
    private Double     stockLivrer          ;
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
	private String siteCode;
	private String siteLibelle;
	private String livraisonTypeCode;
	private String livraisonTypeLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  livraisonTypeIdParam  ;                     
	private SearchParam<Integer>  siteIdParam           ;                     
	private SearchParam<Integer>  productionIdParam     ;                     
	private SearchParam<Double>   stockLivrerParam      ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   siteCodeParam         ;                     
	private SearchParam<String>   siteLibelleParam      ;                     
	private SearchParam<String>   livraisonTypeCodeParam;                     
	private SearchParam<String>   livraisonTypeLibelleParam;                     
    /**
     * Default constructor
     */
    public LivraisonDto()
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
