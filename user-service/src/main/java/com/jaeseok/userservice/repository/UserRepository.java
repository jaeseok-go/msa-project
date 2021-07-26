package com.jaeseok.userservice.repository;

import com.jaeseok.userservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
