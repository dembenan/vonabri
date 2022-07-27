

/*
 * Java transformer for entity table production 
 * Created on 2022-07-27 ( Time 01:31:45 )
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
 * TRANSFORMER for table "production"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ProductionTransformer {

	ProductionTransformer INSTANCE = Mappers.getMapper(ProductionTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.date", dateFormat="dd/MM/yyyy",target="date"),
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.site.id", target="siteId"),
		@Mapping(source="entity.site.code", target="siteCode"),
		@Mapping(source="entity.site.libelle", target="siteLibelle"),
	})
	ProductionDto toDto(Production entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ProductionDto> toDtos(List<Production> entities) throws ParseException;

    default ProductionDto toLiteDto(Production entity) {
		if (entity == null) {
			return null;
		}
		ProductionDto dto = new ProductionDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<ProductionDto> toLiteDtos(List<Production> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ProductionDto> dtos = new ArrayList<ProductionDto>();
		for (Production entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.date", dateFormat="dd/MM/yyyy",target="date"),
		@Mapping(source="dto.aciditeHuile", target="aciditeHuile"),
		@Mapping(source="dto.regimesMalEgra", target="regimesMalEgra"),
		@Mapping(source="dto.cagesCarreau", target="cagesCarreau"),
		@Mapping(source="dto.pi", target="pi"),
		@Mapping(source="dto.pvp", target="pvp"),
		@Mapping(source="dto.sortieHuile", target="sortieHuile"),
		@Mapping(source="dto.sortiePalmiste", target="sortiePalmiste"),
		@Mapping(source="dto.stokPalmiste", target="stokPalmiste"),
		@Mapping(source="dto.regimesTraiter", target="regimesTraiter"),
		@Mapping(source="dto.productionHuile", target="productionHuile"),
		@Mapping(source="dto.productionPalmiste", target="productionPalmiste"),
		@Mapping(source="dto.tauxExtractionHuile", target="tauxExtractionHuile"),
		@Mapping(source="dto.tauxExtractionPalmiste", target="tauxExtractionPalmiste"),
		@Mapping(source="dto.sortieHuileDeSarci", target="sortieHuileDeSarci"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="site", target="site"),
	})
    Production toEntity(ProductionDto dto, Site site) throws ParseException;

    //List<Production> toEntities(List<ProductionDto> dtos) throws ParseException;

}
