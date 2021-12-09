package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.SantaUser;
import org.modelmapper.ModelMapper;


public class UserMapper {


    static ModelMapper modelMapper;

    public static SantaUser mapToSantaUserFromSantaUserDto(SantaUserDto santaUserDto) {
        SantaUser santaUser = new SantaUser();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUserDto, santaUser);
        return santaUser;
    }
    public static SantaUserDto mapSantaUserDtoFromSantaUSer(SantaUser santaUser) {
        SantaUserDto santaUserDto = new SantaUserDto();
        modelMapper = new ModelMapper();
        modelMapper.map(santaUser, santaUserDto);
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



}




