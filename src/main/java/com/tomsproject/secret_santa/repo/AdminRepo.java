package com.tomsproject.secret_santa.repo;

import com.tomsproject.secret_santa.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {

    Optional<Admin> findAdminDtoByEmail(String adminLogin);
    Optional<Admin> findAdminDtoByActivationToken(String token);











}
