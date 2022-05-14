

/*
 * Java transformer for entity table element 
 * Created on 2022-05-05 ( Time 15:22:34 )
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
 * TRANSFORMER for table "element"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ElementTransformer {

	ElementTransformer INSTANCE = Mappers.getMapper(ElementTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.elementType.id", target="elementTypeId"),
		@Mapping(source="entity.elementType.code", target="elementTypeCode"),
		@Mapping(source="entity.elementType.libelle", target="elementTypeLibelle"),
		@Mapping(source="entity.element.id", target="parentId"),
		@Mapping(source="entity.element.code", target="elementCode"),
		@Mapping(source="entity.element.libelle", target="elementLibelle"),
	})
	ElementDto toDto(Element entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ElementDto> toDtos(List<Element> entities) throws ParseException;

    default ElementDto toLiteDto(Element entity) {
		if (entity == null) {
			return null;
		}
		ElementDto dto = new ElementDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<ElementDto> toLiteDtos(List<Element> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ElementDto> dtos = new ArrayList<ElementDto>();
		for (Element entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.icon", target="icon"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="elementType", target="elementType"),
		@Mapping(source="element", target="element"),
	})
    Element toEntity(ElementDto dto, ElementType elementType, Element element) throws ParseException;

    //List<Element> toEntities(List<ElementDto> dtos) throws ParseException;

}
