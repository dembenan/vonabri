

/*
 * Java transformer for entity table statut_matrimonial 
 * Created on 2022-06-11 ( Time 19:24:49 )
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
 * TRANSFORMER for table "statut_matrimonial"
 * 
 * @author Geo
 *
 */
@Mapper
public interface StatutMatrimonialTransformer {

	StatutMatrimonialTransformer INSTANCE = Mappers.getMapper(StatutMatrimonialTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="updatedAt"),
	})
	StatutMatrimonialDto toDto(StatutMatrimonial entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<StatutMatrimonialDto> toDtos(List<StatutMatrimonial> entities) throws ParseException;

    default StatutMatrimonialDto toLiteDto(StatutMatrimonial entity) {
		if (entity == null) {
			return null;
		}
		StatutMatrimonialDto dto = new StatutMatrimonialDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<StatutMatrimonialDto> toLiteDtos(List<StatutMatrimonial> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<StatutMatrimonialDto> dtos = new ArrayList<StatutMatrimonialDto>();
		for (StatutMatrimonial entity : entities) {
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
    StatutMatrimonial toEntity(StatutMatrimonialDto dto) throws ParseException;

    //List<StatutMatrimonial> toEntities(List<StatutMatrimonialDto> dtos) throws ParseException;

}
