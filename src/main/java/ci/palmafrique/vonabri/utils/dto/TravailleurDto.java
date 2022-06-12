
/*
 * Java dto for entity table travailleur 
 * Created on 2022-06-11 ( Time 19:24:49 )
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
import ci.palmafrique.vonabri.utils.dto.customize._TravailleurDto;

/**
 * DTO for table "travailleur"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class TravailleurDto extends _TravailleurDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     nom                  ;
    private String     prenom               ;
    private String     email                ;
    private String     contact1             ;
    private String     contact2             ;
    private String     domicile             ;
	private String     dateEmbauche         ;
    private String     matricule            ;
    private Integer    siteId               ;
    private Integer    sousSiteId           ;
    private Integer    statutId             ;
    private Integer    employeurId          ;
    private Integer    directionId          ;
    private Integer    fonctionId           ;
    private Integer    posteDeTravailId     ;
    private Integer    sousPosteDeTravailId ;
    private Integer    ancienneteSocieteId  ;
    private Integer    anciennetePosteId    ;
    private Integer    ethniePereId         ;
    private Integer    ethnieMereId         ;
    private Integer    statutMatrimonialId  ;
    private Integer    typeMariageId        ;
    private Integer    regimeId             ;
    private Integer    gestionDeBienId      ;
    private Integer    nombreEpouse         ;
    private Integer    nombreEnfants        ;
    private Integer    nombreFilles         ;
    private Integer    nombreGarcons        ;
    private Integer    nombreAdoption       ;
    private Integer    nombreEnfantSousTutelleDirect ;
    private Integer    nombreEnfantSousTutelleIndirect ;
	private String     createdAt            ;
	private String     dateDeNaissance            ;

	private String     deletedAt            ;
	private String     updatedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String sousPosteDeTravailCode;
	private String sousPosteDeTravailLibelle;
	private String employeurCode;
	private String employeurLibelle;
	private String siteCode;
	private String siteLibelle;
	private String ancienneteSocieteCode;
	private String ancienneteSocieteLibelle;
	private String regimeCode;
	private String regimeLibelle;
	private String anciennetePosteCode;
	private String anciennetePosteLibelle;
	private String ethniePereCode;
	private String ethniePereLibelle;
	private String statutCode;
	private String statutLibelle;
	private String ethnieMereCode;
	private String ethnieMereLibelle;
	private String gestionDeBienCode;
	private String gestionDeBienLibelle;
	private String posteDeTravailCode;
	private String posteDeTravailLibelle;
	private String fonctionCode;
	private String fonctionLibelle;
	private String directionCode;
	private String directionLibelle;
	private String typeMariageCode;
	private String typeMariageLibelle;
	private String statutMatrimonialCode;
	private String statutMatrimonialLibelle;
	private String sousSiteCode;
	private String sousSiteLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   nomParam              ;                     
	private SearchParam<String>   prenomParam           ;                     
	private SearchParam<String>   emailParam            ;                     
	private SearchParam<String>   contact1Param         ;                     
	private SearchParam<String>   contact2Param         ;                     
	private SearchParam<String>   domicileParam         ;                     
	private SearchParam<String>   dateEmbaucheParam     ;                     
	private SearchParam<String>   matriculeParam        ; 
	private SearchParam<String>   dateDeNaissanceParam        ;                     
	private SearchParam<Integer>  siteIdParam           ;                     
	private SearchParam<Integer>  sousSiteIdParam       ;                     
	private SearchParam<Integer>  statutIdParam         ;                     
	private SearchParam<Integer>  employeurIdParam      ;                     
	private SearchParam<Integer>  directionIdParam      ;                     
	private SearchParam<Integer>  fonctionIdParam       ;                     
	private SearchParam<Integer>  posteDeTravailIdParam ;                     
	private SearchParam<Integer>  sousPosteDeTravailIdParam;                     
	private SearchParam<Integer>  ancienneteSocieteIdParam;                     
	private SearchParam<Integer>  anciennetePosteIdParam;                     
	private SearchParam<Integer>  ethniePereIdParam     ;                     
	private SearchParam<Integer>  ethnieMereIdParam     ;                     
	private SearchParam<Integer>  statutMatrimonialIdParam;                     
	private SearchParam<Integer>  typeMariageIdParam    ;                     
	private SearchParam<Integer>  regimeIdParam         ;                     
	private SearchParam<Integer>  gestionDeBienIdParam  ;                     
	private SearchParam<Integer>  nombreEpouseParam     ;                     
	private SearchParam<Integer>  nombreEnfantsParam    ;                     
	private SearchParam<Integer>  nombreFillesParam     ;                     
	private SearchParam<Integer>  nombreGarconsParam    ;                     
	private SearchParam<Integer>  nombreAdoptionParam   ;                     
	private SearchParam<Integer>  nombreEnfantSousTutelleDirectParam;                     
	private SearchParam<Integer>  nombreEnfantSousTutelleIndirectParam;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   sousPosteDeTravailCodeParam;                     
	private SearchParam<String>   sousPosteDeTravailLibelleParam;                     
	private SearchParam<String>   employeurCodeParam    ;                     
	private SearchParam<String>   employeurLibelleParam ;                     
	private SearchParam<String>   siteCodeParam         ;                     
	private SearchParam<String>   siteLibelleParam      ;                     
	private SearchParam<String>   anciennetePosteCodeParam   ;                     
	private SearchParam<String>   anciennetePosteLibelleParam;                     
	private SearchParam<String>   regimeCodeParam       ;                     
	private SearchParam<String>   regimeLibelleParam    ;                     
	private SearchParam<String>   ancienneteSocieteCodeParam   ;                     
	private SearchParam<String>   ancienneteSocieteLibelleParam;                     
	private SearchParam<String>   ethniePereCodeParam       ;                     
	private SearchParam<String>   ethniePereLibelleParam    ;                     
	private SearchParam<String>   statutCodeParam       ;                     
	private SearchParam<String>   statutLibelleParam    ;                     
	private SearchParam<String>   ethnieMereCodeParam       ;                     
	private SearchParam<String>   ethnieMereLibelleParam    ;                     
	private SearchParam<String>   gestionDeBienCodeParam;                     
	private SearchParam<String>   gestionDeBienLibelleParam;                     
	private SearchParam<String>   posteDeTravailCodeParam;                     
	private SearchParam<String>   posteDeTravailLibelleParam;                     
	private SearchParam<String>   fonctionCodeParam     ;                     
	private SearchParam<String>   fonctionLibelleParam  ;                     
	private SearchParam<String>   directionCodeParam    ;                     
	private SearchParam<String>   directionLibelleParam ;                     
	private SearchParam<String>   typeMariageCodeParam  ;                     
	private SearchParam<String>   typeMariageLibelleParam;                     
	private SearchParam<String>   statutMatrimonialCodeParam;                     
	private SearchParam<String>   statutMatrimonialLibelleParam;                     
	private SearchParam<String>   sousSiteCodeParam     ;                     
	private SearchParam<String>   sousSiteLibelleParam  ;                     
    /**
     * Default constructor
     */
    public TravailleurDto()
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
