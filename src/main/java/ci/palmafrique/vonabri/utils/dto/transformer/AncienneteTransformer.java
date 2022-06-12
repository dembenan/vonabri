

/*
 * Java transformer for entity table anciennete 
 * Created on 2022-06-11 ( Time 19:24:46 )
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
 * TRANSFORMER for table "anciennete"
 * 
 * @author Geo
 *
 */
@Mapper
public interface AncienneteTransformer {

	AncienneteTransformer INSTANCE = Mappers.getMapper(AncienneteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.ancienneteType.id", target="ancienneteTypeId"),
		@Mapping(source="entity.ancienneteType.code", target="ancienneteTypeCode"),
		@Mapping(source="entity.ancienneteType.libelle", target="ancienneteTypeLibelle"),
	})
	AncienneteDto toDto(Anciennete entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AncienneteDto> toDtos(List<Anciennete> entities) throws ParseException;

    default AncienneteDto toLiteDto(Anciennete entity) {
		if (entity == null) {
			return null;
		}
		AncienneteDto dto = new AncienneteDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<AncienneteDto> toLiteDtos(List<Anciennete> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AncienneteDto> dtos = new ArrayList<AncienneteDto>();
		for (Anciennete entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="ancienneteType", target="ancienneteType"),
	})
    Anciennete toEntity(AncienneteDto dto, AncienneteType ancienneteType) throws ParseException;

    //List<Anciennete> toEntities(List<AncienneteDto> dtos) throws ParseException;

}
