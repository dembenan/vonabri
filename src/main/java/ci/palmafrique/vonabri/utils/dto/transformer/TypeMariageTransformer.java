

/*
 * Java transformer for entity table type_mariage 
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
 * TRANSFORMER for table "type_mariage"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TypeMariageTransformer {

	TypeMariageTransformer INSTANCE = Mappers.getMapper(TypeMariageTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="updatedAt"),
	})
	TypeMariageDto toDto(TypeMariage entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TypeMariageDto> toDtos(List<TypeMariage> entities) throws ParseException;

    default TypeMariageDto toLiteDto(TypeMariage entity) {
		if (entity == null) {
			return null;
		}
		TypeMariageDto dto = new TypeMariageDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<TypeMariageDto> toLiteDtos(List<TypeMariage> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TypeMariageDto> dtos = new ArrayList<TypeMariageDto>();
		for (TypeMariage entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    TypeMariage toEntity(TypeMariageDto dto) throws ParseException;

    //List<TypeMariage> toEntities(List<TypeMariageDto> dtos) throws ParseException;

}
