package com.tomsproject.secret_santa.repo;

import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameRepo extends JpaRepository<GameEntity,Long> {

    @Query(value = "select count(u) from GameEntity u where u.adminDto.adminId=:keyword and u.gameCompleted=:isCompleted ")
    long countGames(@Param("keyword") long adminId,@Param("isCompleted") boolean isCompleted  );


    @Query(value = "select u from GameEntity  u  join fetch u.adminDto join fetch u.userList where  u.isStartNow=false and u.isFirstMessageSent=false  and u.startDate<=:dateTime ")

    Optional<GameEntity> isAfterStartDate(@Param("dateTime") LocalDateTime dateTime  );



    @Query(value = "select  u  from GameEntity u join fetch u.userList " +
            "where  u.lastResponseDate<=:dateTime and u.gameCompleted=false ")
    Optional<GameEntity> getNotCompletedGameAfterLastResponseDate(@Param("dateTime") LocalDateTime dateTime);

    @Query(value = " select u.userList from GameEntity u where u.gameId=:gameId and u.gameCompleted=:isCompleted" )
    Optional<List<SantaUserEntity>> findGamesUserByGameIdAndComplete(@Param("gameId") Long gameId, @Param("isCompleted") boolean isCompleted);

    @Query(value = " select u from GameEntity u where u.adminDto.adminId=:adminId and u.gameCompleted=:isCompleted" )
    Optional <List<GameEntity>> findGamesByAdminAndComplete(@Param("adminId") Long adminId, @Param("isCompleted") boolean isCompleted);






}
