package com.jaeseok.userservice.service;

import com.jaeseok.userservice.dto.UserDto;
import com.jaeseok.userservice.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createdUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
    UserDto getUserDetailsByEmail(String email);
}
