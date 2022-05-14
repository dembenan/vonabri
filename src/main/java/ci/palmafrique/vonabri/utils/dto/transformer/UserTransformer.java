

/*
 * Java transformer for entity table user 
 * Created on 2022-04-25 ( Time 03:17:05 )
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
 * TRANSFORMER for table "user"
 * 
 * @author Geo
 *
 */
@Mapper
public interface UserTransformer {

	UserTransformer INSTANCE = Mappers.getMapper(UserTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.userType.id", target="userTypeId"),
		@Mapping(source="entity.userType.code", target="userTypeCode"),
		@Mapping(source="entity.userType.libelle", target="userTypeLibelle"),
		@Mapping(source="entity.profil.id", target="profilId"),
		@Mapping(source="entity.profil.code", target="profilCode"),
		@Mapping(source="entity.profil.libelle", target="profilLibelle"),
	})
	UserDto toDto(User entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UserDto> toDtos(List<User> entities) throws ParseException;

    default UserDto toLiteDto(User entity) {
		if (entity == null) {
			return null;
		}
		UserDto dto = new UserDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<UserDto> toLiteDtos(List<User> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.email", target="email"),
		@Mapping(source="dto.password", target="password"),
		@Mapping(source="dto.isLocked", target="isLocked"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.isConnected", target="isConnected"),
		@Mapping(source="userType", target="userType"),
		@Mapping(source="profil", target="profil"),
	})
    User toEntity(UserDto dto, UserType userType, Profil profil) throws ParseException;

    //List<User> toEntities(List<UserDto> dtos) throws ParseException;

}
