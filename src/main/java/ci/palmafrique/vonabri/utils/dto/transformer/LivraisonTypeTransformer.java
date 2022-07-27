

/*
 * Java transformer for entity table livraison_type 
 * Created on 2022-07-27 ( Time 01:05:43 )
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
 * TRANSFORMER for table "livraison_type"
 * 
 * @author Geo
 *
 */
@Mapper
public interface LivraisonTypeTransformer {

	LivraisonTypeTransformer INSTANCE = Mappers.getMapper(LivraisonTypeTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	LivraisonTypeDto toDto(LivraisonType entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<LivraisonTypeDto> toDtos(List<LivraisonType> entities) throws ParseException;

    default LivraisonTypeDto toLiteDto(LivraisonType entity) {
		if (entity == null) {
			return null;
		}
		LivraisonTypeDto dto = new LivraisonTypeDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<LivraisonTypeDto> toLiteDtos(List<LivraisonType> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<LivraisonTypeDto> dtos = new ArrayList<LivraisonTypeDto>();
		for (LivraisonType entity : entities) {
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
    LivraisonType toEntity(LivraisonTypeDto dto) throws ParseException;

    //List<LivraisonType> toEntities(List<LivraisonTypeDto> dtos) throws ParseException;

}
