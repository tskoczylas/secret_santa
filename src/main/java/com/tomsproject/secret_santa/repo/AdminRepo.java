package com.tomsproject.secret_santa.repo;

import com.tomsproject.secret_santa.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepo extends JpaRepository<AdminEntity,Long> {

    Optional<AdminEntity> findAdminDtoByEmail(String adminLogin);
    Optional<AdminEntity> findAdminDtoByActivationToken(String token);











}
