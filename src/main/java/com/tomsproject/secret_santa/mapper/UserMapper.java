package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.model.CreateUserDto;
import com.tomsproject.secret_santa.model.GameUserDto;
import com.tomsproject.secret_santa.model.TokenUserDto;
import org.modelmapper.ModelMapper;


public class UserMapper {

    private UserMapper() {
    }

   private static ModelMapper modelMapper;

    public static TokenUserDto mapToTokenUserFromSantaUserDto(SantaUserEntity santaUserEntity) {
        TokenUserDto tokenUserDto = new TokenUserDto();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserEntity, tokenUserDto);
        return tokenUserDto;
    }
    public static SantaUserEntity mapToSantaUserDtoFromTokenUser(TokenUserDto tokenUserDto) {
        SantaUserEntity santaUserEntity = new SantaUserEntity();
        modelMapper = new ModelMapper();
        modelMapper.map(tokenUserDto, santaUserEntity);
        return santaUserEntity;
    }

    public static CreateUserDto mapToCreateUserFromSantaUserDto(SantaUserEntity santaUserEntity) {
        CreateUserDto createUserDto = new CreateUserDto();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserEntity, createUserDto);
        return createUserDto;
    }

    public static SantaUserEntity mapToSantaUserDtoFromCreateUser(CreateUserDto createUserDto) {
        SantaUserEntity santaUserEntity = new SantaUserEntity();
        modelMapper = new ModelMapper();
        modelMapper.map(createUserDto, santaUserEntity);
        return santaUserEntity;
    }

    public static GameUserDto mapToGameUserFromSantaUserDto(SantaUserEntity santaUserEntity) {
        GameUserDto gameUserDto = new GameUserDto();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserEntity, gameUserDto);
        return gameUserDto;
    }



}




