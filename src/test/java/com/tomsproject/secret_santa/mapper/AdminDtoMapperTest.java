package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.Admin;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AdminMapperTest {

    @Test
    public void ShouldReturnAdminFromAdminDto(){
        Admin adminDto = new Admin();
        adminDto.setAdminId(1);
        List<SantaUserEntity> santaUserDtoListEntity = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SantaUserEntity santaUserEntity = new SantaUserEntity();
            santaUserEntity.setUserid(i);
            santaUserDtoListEntity.add(santaUserEntity);
        }

    }

}