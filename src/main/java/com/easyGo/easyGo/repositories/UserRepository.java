package com.easyGo.easyGo.repositories;

import com.easyGo.easyGo.entities.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(String email);
    Boolean existsByEmail(String email);
}
