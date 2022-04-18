package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.model.CreateUserDto;
import com.tomsproject.secret_santa.model.GameDto;
import org.modelmapper.ModelMapper;

public class GameMapper {

    private  GameMapper() {
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static GameEntity mapToGameDtoFromCreateUser(CreateUserDto createUserDto) {
        GameEntity gameDto = new GameEntity();
        modelMapper = new ModelMapper();
        modelMapper.map(createUserDto, gameDto);
        return gameDto;
    }

    public static GameDto mapToGameFromGameDto(GameEntity gameEntity) {
        GameDto gameDto = new GameDto();
        modelMapper = new ModelMapper();
        modelMapper.map(gameEntity, gameDto);
        return gameDto;
    }

}
