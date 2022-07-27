

/*
 * Java transformer for entity table livraison 
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
 * TRANSFORMER for table "livraison"
 * 
 * @author Geo
 *
 */
@Mapper
public interface LivraisonTransformer {

	LivraisonTransformer INSTANCE = Mappers.getMapper(LivraisonTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.production.id", target="productionId"),
		@Mapping(source="entity.site.id", target="siteId"),
		@Mapping(source="entity.site.code", target="siteCode"),
		@Mapping(source="entity.site.libelle", target="siteLibelle"),
		@Mapping(source="entity.livraisonType.id", target="livraisonTypeId"),
		@Mapping(source="entity.livraisonType.code", target="livraisonTypeCode"),
		@Mapping(source="entity.livraisonType.libelle", target="livraisonTypeLibelle"),
	})
	LivraisonDto toDto(Livraison entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<LivraisonDto> toDtos(List<Livraison> entities) throws ParseException;

    default LivraisonDto toLiteDto(Livraison entity) {
		if (entity == null) {
			return null;
		}
		LivraisonDto dto = new LivraisonDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<LivraisonDto> toLiteDtos(List<Livraison> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<LivraisonDto> dtos = new ArrayList<LivraisonDto>();
		for (Livraison entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.stockLivrer", target="stockLivrer"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="production", target="production"),
		@Mapping(source="site", target="site"),
		@Mapping(source="livraisonType", target="livraisonType"),
	})
    Livraison toEntity(LivraisonDto dto, Production production, Site site, LivraisonType livraisonType) throws ParseException;

    //List<Livraison> toEntities(List<LivraisonDto> dtos) throws ParseException;

}
