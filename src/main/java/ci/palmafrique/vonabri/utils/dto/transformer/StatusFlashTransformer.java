

/*
 * Java transformer for entity table status_flash 
 * Created on 2022-07-28 ( Time 22:56:45 )
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
 * TRANSFORMER for table "status_flash"
 * 
 * @author Geo
 *
 */
@Mapper
public interface StatusFlashTransformer {

	StatusFlashTransformer INSTANCE = Mappers.getMapper(StatusFlashTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	StatusFlashDto toDto(StatusFlash entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<StatusFlashDto> toDtos(List<StatusFlash> entities) throws ParseException;

    default StatusFlashDto toLiteDto(StatusFlash entity) {
		if (entity == null) {
			return null;
		}
		StatusFlashDto dto = new StatusFlashDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<StatusFlashDto> toLiteDtos(List<StatusFlash> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<StatusFlashDto> dtos = new ArrayList<StatusFlashDto>();
		for (StatusFlash entity : entities) {
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
	})
    StatusFlash toEntity(StatusFlashDto dto) throws ParseException;

    //List<StatusFlash> toEntities(List<StatusFlashDto> dtos) throws ParseException;

}
