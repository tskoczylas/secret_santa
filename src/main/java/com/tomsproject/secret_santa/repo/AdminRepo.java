package com.tomsproject.secret_santa.repo;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AdminRepo extends JpaRepository<AdminDto,Long> {

    Optional<AdminDto>  findAdminDtoByAdminIdAndItHasBeenDrawnIsFalse(long adminId);





}
