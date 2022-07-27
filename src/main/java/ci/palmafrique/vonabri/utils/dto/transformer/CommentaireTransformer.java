

/*
 * Java transformer for entity table commentaire 
 * Created on 2022-07-27 ( Time 01:05:41 )
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
 * TRANSFORMER for table "commentaire"
 * 
 * @author Geo
 *
 */
@Mapper
public interface CommentaireTransformer {

	CommentaireTransformer INSTANCE = Mappers.getMapper(CommentaireTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.commentaireType.id", target="commentaireTypeId"),
		@Mapping(source="entity.commentaireType.code", target="commentaireTypeCode"),
		@Mapping(source="entity.commentaireType.libelle", target="commentaireTypeLibelle"),
		@Mapping(source="entity.production.id", target="productionId"),
	})
	CommentaireDto toDto(Commentaire entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CommentaireDto> toDtos(List<Commentaire> entities) throws ParseException;

    default CommentaireDto toLiteDto(Commentaire entity) {
		if (entity == null) {
			return null;
		}
		CommentaireDto dto = new CommentaireDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<CommentaireDto> toLiteDtos(List<Commentaire> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CommentaireDto> dtos = new ArrayList<CommentaireDto>();
		for (Commentaire entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.description", target="description"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="commentaireType", target="commentaireType"),
		@Mapping(source="production", target="production"),
	})
    Commentaire toEntity(CommentaireDto dto, CommentaireType commentaireType, Production production) throws ParseException;

    //List<Commentaire> toEntities(List<CommentaireDto> dtos) throws ParseException;

}
