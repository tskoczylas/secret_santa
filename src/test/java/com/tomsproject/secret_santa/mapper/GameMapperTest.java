package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class GameMapperTest {

    Game game;
    GameEntity gameDto;
    SantaUserEntity santaUserEntity;
    List<SantaUserEntity> santaUserEntityList;


    @BeforeEach
    void initialize() {
        Game game = new Game();

         gameDto = new GameEntity();
        gameDto.setGameId(3);
         santaUserEntity = new SantaUserEntity();
       santaUserEntityList = new ArrayList<>();
        gameDto.setUserList(santaUserEntityList);


    }


    @Test
    void mapToGameFromGameDtoS() {
        //given
        //when


        //then



    }
}