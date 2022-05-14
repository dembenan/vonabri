
/*
 * Java dto for entity table profil_fonctionnalite 
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
import ci.palmafrique.vonabri.utils.dto.customize._ProfilFonctionnaliteDto;

/**
 * DTO for table "profil_fonctionnalite"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ProfilFonctionnaliteDto extends _ProfilFonctionnaliteDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     code                 ;
    private String     libelle              ;
    private Integer    profilId             ;
    private Integer    fonctionnaliteId     ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String profilCode;
	private String profilLibelle;
	private String fonctionnaliteCode;
	private String fonctionnaliteLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<Integer>  profilIdParam         ;                     
	private SearchParam<Integer>  fonctionnaliteIdParam ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   profilCodeParam       ;                     
	private SearchParam<String>   profilLibelleParam    ;                     
	private SearchParam<String>   fonctionnaliteCodeParam;                     
	private SearchParam<String>   fonctionnaliteLibelleParam;                     
    /**
     * Default constructor
     */
    public ProfilFonctionnaliteDto()
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
