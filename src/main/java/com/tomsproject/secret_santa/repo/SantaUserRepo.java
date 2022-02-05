package com.tomsproject.secret_santa.repo;

import com.tomsproject.secret_santa.entity.SantaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SantaUserRepo extends JpaRepository<SantaUserEntity,Long> {


    @Query(value = "select count(u) from SantaUserEntity u where u.isUserComplete=true and u.gameDto.gameId = :keyword")
    long countUsersCompletedByAdminId(@Param("keyword") long gameId);

    @Query(value = "select count(u) from SantaUserEntity u where  u.gameDto.gameId = :keyword")
    long countUsersCreatedByAdminID(@Param("keyword") long gameId );




}
