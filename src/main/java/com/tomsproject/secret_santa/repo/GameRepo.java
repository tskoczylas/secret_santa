package com.tomsproject.secret_santa.repo;

import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.mapper.GameMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameRepo extends JpaRepository<GameDto,Long> {

    @Query(value = "select count(u) from GameDto u where u.adminDto.adminId=:keyword and u.gameCompleted=:isCompleted ")
    long countGames(@Param("keyword") long adminId,@Param("isCompleted") boolean isCompleted  );


    @Query(value = "select u from GameDto  u  join fetch u.adminDto join fetch u.userList where  u.isStartNow=false and u.isFirstMessageSent=false  and u.startDate<=:dateTime ")

    Optional<GameDto> isAfterStartDate(@Param("dateTime") LocalDateTime dateTime  );



    @Query(value = "select  u  from GameDto u join fetch u.userList " +
            "where  u.lastResponseDate<=:dateTime and u.gameCompleted=false ")
    Optional<GameDto> getNotCompletedGameAfterLastResponseDate(@Param("dateTime") LocalDateTime dateTime);

    @Query(value = " select u.userList from GameDto u where u.gameId=:gameId and u.gameCompleted=:isCompleted" )
    Optional<List<SantaUserDto>> findGamesUserByGameIdAndComplete(@Param("gameId") Long gameId,@Param("isCompleted") boolean isCompleted);

    @Query(value = " select u from GameDto u where u.adminDto.adminId=:adminId and u.gameCompleted=:isCompleted" )
    Optional <List<GameDto>> findGamesByAdminAndComplete(@Param("adminId") Long adminId,@Param("isCompleted") boolean isCompleted);






}
