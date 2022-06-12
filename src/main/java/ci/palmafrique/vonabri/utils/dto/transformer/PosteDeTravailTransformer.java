

/*
 * Java transformer for entity table poste_de_travail 
 * Created on 2022-06-11 ( Time 19:24:48 )
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
 * TRANSFORMER for table "poste_de_travail"
 * 
 * @author Geo
 *
 */
@Mapper
public interface PosteDeTravailTransformer {

	PosteDeTravailTransformer INSTANCE = Mappers.getMapper(PosteDeTravailTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	PosteDeTravailDto toDto(PosteDeTravail entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PosteDeTravailDto> toDtos(List<PosteDeTravail> entities) throws ParseException;

    default PosteDeTravailDto toLiteDto(PosteDeTravail entity) {
		if (entity == null) {
			return null;
		}
		PosteDeTravailDto dto = new PosteDeTravailDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<PosteDeTravailDto> toLiteDtos(List<PosteDeTravail> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PosteDeTravailDto> dtos = new ArrayList<PosteDeTravailDto>();
		for (PosteDeTravail entity : entities) {
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
    PosteDeTravail toEntity(PosteDeTravailDto dto) throws ParseException;

    //List<PosteDeTravail> toEntities(List<PosteDeTravailDto> dtos) throws ParseException;

}
