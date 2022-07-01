

/*
 * Java transformer for entity table travailleur 
 * Created on 2022-06-11 ( Time 19:24:50 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ci.palmafrique.vonabri.utils.contract.*;
import ci.palmafrique.vonabri.utils.dto.*;
import ci.palmafrique.vonabri.dao.entity.*;


/**
 * TRANSFORMER for table "travailleur"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TravailleurTransformer {

	TravailleurTransformer INSTANCE = Mappers.getMapper(TravailleurTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.dateEmbauche", dateFormat="dd/MM/yyyy",target="dateEmbauche"),
		@Mapping(source="entity.dateFinContrat", dateFormat="dd/MM/yyyy",target="dateFinContrat"),
		@Mapping(source="entity.lieuNaissance", target="lieuNaissance"),

		@Mapping(source="entity.dateDeNaissance", dateFormat="dd/MM/yyyy",target="dateDeNaissance"),
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.sousPosteDeTravail.id", target="sousPosteDeTravailId"),
		@Mapping(source="entity.sousPosteDeTravail.code", target="sousPosteDeTravailCode"),
		@Mapping(source="entity.sousPosteDeTravail.libelle", target="sousPosteDeTravailLibelle"),
		@Mapping(source="entity.employeur.id", target="employeurId"),
		@Mapping(source="entity.employeur.code", target="employeurCode"),
		@Mapping(source="entity.employeur.libelle", target="employeurLibelle"),
		@Mapping(source="entity.site.id", target="siteId"),
		@Mapping(source="entity.site.code", target="siteCode"),
		@Mapping(source="entity.site.libelle", target="siteLibelle"),
		
		@Mapping(source="entity.pays.id", target="paysId"),
		@Mapping(source="entity.pays.code", target="paysCode"),
		@Mapping(source="entity.pays.libelle", target="paysLibelle"),
		
		
		@Mapping(source="entity.civilite.id", target="civiliteId"),
		@Mapping(source="entity.civilite.code", target="civiliteCode"),
		@Mapping(source="entity.civilite.libelle", target="civiliteLibelle"),
		
		
		
		@Mapping(source="entity.ancienneteSociete.id", target="ancienneteSocieteId"),
		@Mapping(source="entity.ancienneteSociete.code", target="ancienneteSocieteCode"),
		@Mapping(source="entity.ancienneteSociete.libelle", target="ancienneteSocieteLibelle"),
		@Mapping(source="entity.regime.id", target="regimeId"),
		@Mapping(source="entity.regime.code", target="regimeCode"),
		@Mapping(source="entity.regime.libelle", target="regimeLibelle"),
		@Mapping(source="entity.anciennetePoste.id", target="anciennetePosteId"),
		@Mapping(source="entity.anciennetePoste.code", target="anciennetePosteCode"),
		@Mapping(source="entity.anciennetePoste.libelle", target="anciennetePosteLibelle"),
		@Mapping(source="entity.ethniePere.id", target="ethniePereId"),
		@Mapping(source="entity.ethniePere.code", target="ethniePereCode"),
		@Mapping(source="entity.ethniePere.libelle", target="ethniePereLibelle"),
		@Mapping(source="entity.statut.id", target="statutId"),
		@Mapping(source="entity.statut.code", target="statutCode"),
		@Mapping(source="entity.statut.libelle", target="statutLibelle"),
		@Mapping(source="entity.ethnieMere.id", target="ethnieMereId"),
		@Mapping(source="entity.ethnieMere.code", target="ethnieMereCode"),
		@Mapping(source="entity.ethnieMere.libelle", target="ethnieMereLibelle"),
		@Mapping(source="entity.gestionDeBien.id", target="gestionDeBienId"),
		@Mapping(source="entity.gestionDeBien.code", target="gestionDeBienCode"),
		@Mapping(source="entity.gestionDeBien.libelle", target="gestionDeBienLibelle"),
		@Mapping(source="entity.posteDeTravail.id", target="posteDeTravailId"),
		@Mapping(source="entity.posteDeTravail.code", target="posteDeTravailCode"),
		@Mapping(source="entity.posteDeTravail.libelle", target="posteDeTravailLibelle"),
		@Mapping(source="entity.fonction.id", target="fonctionId"),
		@Mapping(source="entity.fonction.code", target="fonctionCode"),
		@Mapping(source="entity.fonction.libelle", target="fonctionLibelle"),
		@Mapping(source="entity.direction.id", target="directionId"),
		@Mapping(source="entity.direction.code", target="directionCode"),
		@Mapping(source="entity.direction.libelle", target="directionLibelle"),
		@Mapping(source="entity.typeMariage.id", target="typeMariageId"),
		@Mapping(source="entity.typeMariage.code", target="typeMariageCode"),
		@Mapping(source="entity.typeMariage.libelle", target="typeMariageLibelle"),
		@Mapping(source="entity.statutMatrimonial.id", target="statutMatrimonialId"),
		@Mapping(source="entity.statutMatrimonial.code", target="statutMatrimonialCode"),
		@Mapping(source="entity.statutMatrimonial.libelle", target="statutMatrimonialLibelle"),
		@Mapping(source="entity.sousSite.id", target="sousSiteId"),
		@Mapping(source="entity.sousSite.code", target="sousSiteCode"),
		@Mapping(source="entity.sousSite.libelle", target="sousSiteLibelle"),
	})
	TravailleurDto toDto(Travailleur entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TravailleurDto> toDtos(List<Travailleur> entities) throws ParseException;

    default TravailleurDto toLiteDto(Travailleur entity) {
		if (entity == null) {
			return null;
		}
		TravailleurDto dto = new TravailleurDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		dto.setPrenom( entity.getPrenom() );
		return dto;
    }

	default List<TravailleurDto> toLiteDtos(List<Travailleur> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TravailleurDto> dtos = new ArrayList<TravailleurDto>();
		for (Travailleur entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.prenom", target="prenom"),
		@Mapping(source="dto.email", target="email"),
		@Mapping(source="dto.contact1", target="contact1"),
		@Mapping(source="dto.contact2", target="contact2"),
		@Mapping(source="dto.domicile", target="domicile"),
		@Mapping(source="dto.dateEmbauche", dateFormat="dd/MM/yyyy",target="dateEmbauche"),
		@Mapping(source="dto.dateFinContrat", dateFormat="dd/MM/yyyy",target="dateFinContrat"),
		@Mapping(source="dto.matricule", target="matricule"),
		@Mapping(source="dto.lieuNaissance", target="lieuNaissance"),
		@Mapping(source="dto.nombreEpouse", target="nombreEpouse"),
		@Mapping(source="dto.nombreEnfants", target="nombreEnfants"),
		@Mapping(source="dto.nombreFilles", target="nombreFilles"),
		@Mapping(source="dto.nombreGarcons", target="nombreGarcons"),
		@Mapping(source="dto.nombreAdoption", target="nombreAdoption"),
		@Mapping(source="dto.nombreEnfantSousTutelleDirect", target="nombreEnfantSousTutelleDirect"),
		@Mapping(source="dto.nombreEnfantSousTutelleIndirect", target="nombreEnfantSousTutelleIndirect"),
		@Mapping(source="dto.dateDeNaissance", dateFormat="dd/MM/yyyy",target="dateDeNaissance"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="sousPosteDeTravail", target="sousPosteDeTravail"),
		@Mapping(source="employeur", target="employeur"),
		@Mapping(source="site", target="site"),
		@Mapping(source="anciennetePoste", target="anciennetePoste"),
		@Mapping(source="regime", target="regime"),
		@Mapping(source="ancienneteSociete", target="ancienneteSociete"),
		@Mapping(source="ethniePere", target="ethniePere"),
		@Mapping(source="statut", target="statut"),
		@Mapping(source="ethnieMere", target="ethnieMere"),
		@Mapping(source="gestionDeBien", target="gestionDeBien"),
		@Mapping(source="posteDeTravail", target="posteDeTravail"),
		@Mapping(source="fonction", target="fonction"),
		@Mapping(source="direction", target="direction"),
		@Mapping(source="typeMariage", target="typeMariage"),
		@Mapping(source="statutMatrimonial", target="statutMatrimonial"),
		@Mapping(source="sousSite", target="sousSite"),
		@Mapping(source="pays", target="pays"),
		@Mapping(source="civilite", target="civilite"),
	})
    Travailleur toEntity(TravailleurDto dto, SousPosteDeTravail sousPosteDeTravail, Employeur employeur, Site site, Anciennete anciennetePoste, Regime regime, Anciennete ancienneteSociete, Ethnie ethniePere, Statut statut, Ethnie ethnieMere, GestionDeBien gestionDeBien, PosteDeTravail posteDeTravail, Fonction fonction, Direction direction, TypeMariage typeMariage, StatutMatrimonial statutMatrimonial, SousSite sousSite,Pays pays,Civilite civilite) throws ParseException;

    //List<Travailleur> toEntities(List<TravailleurDto> dtos) throws ParseException;

}
