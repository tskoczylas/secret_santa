package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.Admin;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tomsproject.secret_santa.mapper.AdminMapper.mapToAdminFromAdminDto;
import static org.junit.jupiter.api.Assertions.*;

class AdminMapperTest {

    @Test
    public void ShouldReturnAdminFromAdminDto(){
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(1);
        List<SantaUserDto> santaUserDtoListDto = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SantaUserDto santaUserDto = new SantaUserDto();
            santaUserDto.setUserid(i);
            santaUserDtoListDto.add(santaUserDto);
        }

    }

}