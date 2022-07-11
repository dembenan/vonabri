

/*
 * Java transformer for entity table travailleur_nationnalite 
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
 * TRANSFORMER for table "travailleur_nationnalite"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TravailleurNationnaliteTransformer {

	TravailleurNationnaliteTransformer INSTANCE = Mappers.getMapper(TravailleurNationnaliteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.nationnalite.id", target="nationnaliteId"),
		@Mapping(source="entity.nationnalite.code", target="nationnaliteCode"),
		@Mapping(source="entity.nationnalite.libelle", target="nationnaliteLibelle"),
		@Mapping(source="entity.travailleur.id", target="travailleurId"),
		@Mapping(source="entity.travailleur.nom", target="travailleurNom"),
		@Mapping(source="entity.travailleur.prenom", target="travailleurPrenom"),
	})
	TravailleurNationnaliteDto toDto(TravailleurNationnalite entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TravailleurNationnaliteDto> toDtos(List<TravailleurNationnalite> entities) throws ParseException;

    default TravailleurNationnaliteDto toLiteDto(TravailleurNationnalite entity) {
		if (entity == null) {
			return null;
		}
		TravailleurNationnaliteDto dto = new TravailleurNationnaliteDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<TravailleurNationnaliteDto> toLiteDtos(List<TravailleurNationnalite> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TravailleurNationnaliteDto> dtos = new ArrayList<TravailleurNationnaliteDto>();
		for (TravailleurNationnalite entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="nationnalite", target="nationnalite"),
		@Mapping(source="travailleur", target="travailleur"),
	})
    TravailleurNationnalite toEntity(TravailleurNationnaliteDto dto, Nationnalite nationnalite, Travailleur travailleur) throws ParseException;

    //List<TravailleurNationnalite> toEntities(List<TravailleurNationnaliteDto> dtos) throws ParseException;

}
