

/*
 * Java transformer for entity table unite_mesure 
 * Created on 2022-07-27 ( Time 04:40:27 )
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
 * TRANSFORMER for table "unite_mesure"
 * 
 * @author Geo
 *
 */
@Mapper
public interface UniteMesureTransformer {

	UniteMesureTransformer INSTANCE = Mappers.getMapper(UniteMesureTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	UniteMesureDto toDto(UniteMesure entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UniteMesureDto> toDtos(List<UniteMesure> entities) throws ParseException;

    default UniteMesureDto toLiteDto(UniteMesure entity) {
		if (entity == null) {
			return null;
		}
		UniteMesureDto dto = new UniteMesureDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		return dto;
    }

	default List<UniteMesureDto> toLiteDtos(List<UniteMesure> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UniteMesureDto> dtos = new ArrayList<UniteMesureDto>();
		for (UniteMesure entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.symbol", target="symbol"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
	})
    UniteMesure toEntity(UniteMesureDto dto) throws ParseException;

    //List<UniteMesure> toEntities(List<UniteMesureDto> dtos) throws ParseException;

}
