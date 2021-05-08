package com.su.mapstruct.convert;

import com.su.mapstruct.model.dto.UserDTO;
import com.su.mapstruct.model.eo.UserEO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

;

/**
 * @author xusu
 * @since 2021/4/28
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper( UserConvert.class );

    // eo 转dto
    @Mapping(target = "now",expression = "java(java.time.LocalDateTime.now())")
    UserEO dtoToEo(UserDTO car);
    // dto 转eo
    UserDTO eoToDto(UserEO car);
    // eos 转dtos
    List<UserEO> dtosToEos(List<UserDTO> car);
    // dto 转eo
    List<UserDTO> eosToDtos(List<UserEO> car);
}
