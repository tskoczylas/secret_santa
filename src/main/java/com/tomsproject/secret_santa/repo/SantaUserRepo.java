package com.tomsproject.secret_santa.repo;

import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.SantaUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface SantaUserRepo extends JpaRepository<SantaUserDto,Long> {

    List<SantaUserDto> findAllByAdminDtoAdminId(long adminId);
    Optional<SantaUserDto> findSantaUserDtoByToken(String token);
@Query(value = "select u from SantaUserDto u where u.isUserComplete=false and u.isUserCreate=true and u.token is null")
    Optional<SantaUserDto> findSantaUserDtoByIsUserCompleteFalseAndIsUserCreateTrue();


    @Query(value = "select count(u) from SantaUserDto u where   u.isUserCreate=true and u.adminDto.adminId = :keyword")
    long countUsersCreatedByAdminID(@Param("keyword") long adminId );

    @Query(value = "select count(u) from SantaUserDto u where u.isUserComplete=true and u.isUserCreate=true and  u.adminDto.adminId = :keyword")
    long countUsersCompletedByAdminId(@Param("keyword") long adminId );






}
