package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.GameUser;
import com.tomsproject.secret_santa.model.TokenUser;
import org.modelmapper.ModelMapper;


public class UserMapper {

    private UserMapper() {
    }

   private static ModelMapper modelMapper;

    public static TokenUser mapToTokenUserFromSantaUserDto(SantaUserEntity santaUserEntity) {
        TokenUser tokenUser = new TokenUser();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserEntity, tokenUser);
        return tokenUser;
    }
    public static SantaUserEntity mapToSantaUserDtoFromTokenUser(TokenUser tokenUser) {
        SantaUserEntity santaUserEntity = new SantaUserEntity();
        modelMapper = new ModelMapper();
        modelMapper.map(tokenUser, santaUserEntity);
        return santaUserEntity;
    }

    public static CreateUser mapToCreateUserFromSantaUserDto(SantaUserEntity santaUserEntity) {
        CreateUser createUser = new CreateUser();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserEntity, createUser);
        return createUser;
    }

    public static SantaUserEntity mapToSantaUserDtoFromCreateUser(CreateUser createUser) {
        SantaUserEntity santaUserEntity = new SantaUserEntity();
        modelMapper = new ModelMapper();
        modelMapper.map(createUser, santaUserEntity);
        return santaUserEntity;
    }

    public static GameUser mapToGameUserFromSantaUserDto(SantaUserEntity santaUserEntity) {
        GameUser gameUser = new GameUser();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserEntity, gameUser);
        return gameUser;
    }



}




