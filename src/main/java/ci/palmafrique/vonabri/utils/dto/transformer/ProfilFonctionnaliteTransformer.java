

/*
 * Java transformer for entity table profil_fonctionnalite 
 * Created on 2022-04-25 ( Time 03:17:04 )
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
 * TRANSFORMER for table "profil_fonctionnalite"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ProfilFonctionnaliteTransformer {

	ProfilFonctionnaliteTransformer INSTANCE = Mappers.getMapper(ProfilFonctionnaliteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.profil.id", target="profilId"),
		@Mapping(source="entity.profil.code", target="profilCode"),
		@Mapping(source="entity.profil.libelle", target="profilLibelle"),
		@Mapping(source="entity.fonctionnalite.id", target="fonctionnaliteId"),
		@Mapping(source="entity.fonctionnalite.code", target="fonctionnaliteCode"),
		@Mapping(source="entity.fonctionnalite.libelle", target="fonctionnaliteLibelle"),
	})
	ProfilFonctionnaliteDto toDto(ProfilFonctionnalite entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ProfilFonctionnaliteDto> toDtos(List<ProfilFonctionnalite> entities) throws ParseException;

    default ProfilFonctionnaliteDto toLiteDto(ProfilFonctionnalite entity) {
		if (entity == null) {
			return null;
		}
		ProfilFonctionnaliteDto dto = new ProfilFonctionnaliteDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<ProfilFonctionnaliteDto> toLiteDtos(List<ProfilFonctionnalite> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ProfilFonctionnaliteDto> dtos = new ArrayList<ProfilFonctionnaliteDto>();
		for (ProfilFonctionnalite entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="profil", target="profil"),
		@Mapping(source="fonctionnalite", target="fonctionnalite"),
	})
    ProfilFonctionnalite toEntity(ProfilFonctionnaliteDto dto, Profil profil, Fonctionnalite fonctionnalite) throws ParseException;

    //List<ProfilFonctionnalite> toEntities(List<ProfilFonctionnaliteDto> dtos) throws ParseException;

}
