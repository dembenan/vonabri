
/*
 * Java dto for entity table production 
 * Created on 2022-07-27 ( Time 01:31:45 )
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
import ci.palmafrique.vonabri.utils.dto.customize._ProductionDto;

/**
 * DTO for table "production"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ProductionDto extends _ProductionDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    siteId               ;
	private String     date                 ;
    private Double     aciditeHuile         ;
    private Double     regimesMalEgra       ;
    private String     cagesCarreau         ;
    private Double     pi                   ;
    private Double     pvp                  ;
    private Double     sortieHuile          ;
    private Double     sortiePalmiste       ;
    private Double     stokPalmiste         ;
    private Double     regimesTraiter       ;
    private Double     productionHuile      ;
    private Double     productionPalmiste   ;
    private Double     tauxExtractionHuile  ;
    private Double     tauxExtractionPalmiste ;
    private Double     sortieHuileDeSarci   ;
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

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  siteIdParam           ;                     
	private SearchParam<String>   dateParam             ;                     
	private SearchParam<Double>   aciditeHuileParam     ;                     
	private SearchParam<Double>   regimesMalEgraParam   ;                     
	private SearchParam<String>   cagesCarreauParam     ;                     
	private SearchParam<Double>   piParam               ;                     
	private SearchParam<Double>   pvpParam              ;                     
	private SearchParam<Double>   sortieHuileParam      ;                     
	private SearchParam<Double>   sortiePalmisteParam   ;                     
	private SearchParam<Double>   stokPalmisteParam     ;                     
	private SearchParam<Double>   regimesTraiterParam   ;                     
	private SearchParam<Double>   productionHuileParam  ;                     
	private SearchParam<Double>   productionPalmisteParam;                     
	private SearchParam<Double>   tauxExtractionHuileParam;                     
	private SearchParam<Double>   tauxExtractionPalmisteParam;                     
	private SearchParam<Double>   sortieHuileDeSarciParam;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   siteCodeParam         ;                     
	private SearchParam<String>   siteLibelleParam      ;                     
    /**
     * Default constructor
     */
    public ProductionDto()
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
