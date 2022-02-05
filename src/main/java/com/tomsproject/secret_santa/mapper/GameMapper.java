package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.Game;
import org.modelmapper.ModelMapper;

public class GameMapper {

    private  GameMapper() {
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static GameEntity mapToGameDtoFromCreateUser(CreateUser createUser) {
        GameEntity gameDto = new GameEntity();
        modelMapper = new ModelMapper();
        modelMapper.map(createUser, gameDto);
        return gameDto;
    }

    public static Game mapToGameFromGameDto(GameEntity gameEntity) {
        Game game = new Game();
        modelMapper = new ModelMapper();
        modelMapper.map(gameEntity, game);
        return game;
    }

}
