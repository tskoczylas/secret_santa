package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.GameUser;
import com.tomsproject.secret_santa.model.TokenUser;
import org.modelmapper.ModelMapper;


public class UserMapper {


    static ModelMapper modelMapper;

    public static TokenUser mapToTokenUserFromSantaUserDto(SantaUserDto santaUserDto) {
        TokenUser tokenUser = new TokenUser();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserDto, tokenUser);
        return tokenUser;
    }
    public static SantaUserDto mapToSantaUserDtoFromTokenUser(TokenUser tokenUser) {
        SantaUserDto santaUserDto = new SantaUserDto();
        modelMapper = new ModelMapper();
        modelMapper.map(tokenUser, santaUserDto);
        return santaUserDto;
    }

    public static CreateUser mapToCreateUserFromSantaUserDto(SantaUserDto santaUserDto) {
        CreateUser createUser = new CreateUser();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserDto, createUser);
        return createUser;
    }

    public static SantaUserDto mapToSantaUserDtoFromCreateUser(CreateUser createUser) {
        SantaUserDto santaUserDto = new SantaUserDto();
        modelMapper = new ModelMapper();
        modelMapper.map(createUser, santaUserDto);
        return santaUserDto;
    }

    public static GameUser mapToGameUserFromSantaUserDto(SantaUserDto santaUserDto) {
        GameUser gameUser = new GameUser();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserDto, gameUser);
        return gameUser;
    }



}




