package com.jaeseok.userservice.mapper;

import com.jaeseok.userservice.dto.UserDto;
import com.jaeseok.userservice.entity.UserEntity;
import com.jaeseok.userservice.vo.RequestUser;
import com.jaeseok.userservice.vo.ResponseUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto requestToDto(RequestUser requestUser);
    ResponseUser dtoToResponse(UserDto userDto);
    UserEntity dtoToEntity(UserDto userDto);
    UserDto entityToDto(UserEntity userEntity);
}
