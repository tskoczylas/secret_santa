package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.Game;
import com.tomsproject.secret_santa.model.GameUser;
import org.modelmapper.ModelMapper;

public class GameMapper {

    //toDo tests

    static ModelMapper modelMapper = new ModelMapper();

    public static GameDto mapToGameDtoFromCreateUser(CreateUser createUser) {
        GameDto gameDto = new GameDto();
        modelMapper = new ModelMapper();
        modelMapper.map(createUser, gameDto);
        return gameDto;
    }

    public static Game mapToGameFromGameDto(GameDto gameDto) {
        Game game = new Game();
        modelMapper = new ModelMapper();
        modelMapper.map(gameDto, game);
        return game;
    }

}
