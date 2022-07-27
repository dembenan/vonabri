

/*
 * Java transformer for entity table stock_huile 
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
 * TRANSFORMER for table "stock_huile"
 * 
 * @author Geo
 *
 */
@Mapper
public interface StockHuileTransformer {

	StockHuileTransformer INSTANCE = Mappers.getMapper(StockHuileTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.production.id", target="productionId"),
		@Mapping(source="entity.tank.id", target="tankId"),
		@Mapping(source="entity.tank.code", target="tankCode"),
		@Mapping(source="entity.tank.libelle", target="tankLibelle"),
	})
	StockHuileDto toDto(StockHuile entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<StockHuileDto> toDtos(List<StockHuile> entities) throws ParseException;

    default StockHuileDto toLiteDto(StockHuile entity) {
		if (entity == null) {
			return null;
		}
		StockHuileDto dto = new StockHuileDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<StockHuileDto> toLiteDtos(List<StockHuile> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<StockHuileDto> dtos = new ArrayList<StockHuileDto>();
		for (StockHuile entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.stock", target="stock"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="production", target="production"),
		@Mapping(source="tank", target="tank"),
	})
    StockHuile toEntity(StockHuileDto dto, Production production, Tank tank) throws ParseException;

    //List<StockHuile> toEntities(List<StockHuileDto> dtos) throws ParseException;

}
