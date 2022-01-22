package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.Game;
import com.tomsproject.secret_santa.model.GameUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tomsproject.secret_santa.mapper.GameMapper.mapToGameFromGameDto;
import static org.hamcrest.MatcherAssert.assertThat;

class GameMapperTest {

    Game game;
    GameDto gameDto;
    SantaUserDto santaUserDto;
    List<SantaUserDto> santaUserDtoList;


    @BeforeEach
    void initialize() {
        Game game = new Game();

         gameDto = new GameDto();
        gameDto.setGameId(3);
         santaUserDto = new SantaUserDto();
       santaUserDtoList = new ArrayList<>();
        gameDto.setUserList(santaUserDtoList);


    }


    @Test
    void mapToGameFromGameDtoS() {
        //given
        //when


        //then



    }
}