package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.AdminEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AdminMapperTest {

    @Test
    public void ShouldReturnAdminFromAdminDto(){
        AdminEntity adminDto = new AdminEntity();
        adminDto.setAdminId(1);
        List<SantaUserEntity> santaUserDtoListEntity = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SantaUserEntity santaUserEntity = new SantaUserEntity();
            santaUserEntity.setUserid(i);
            santaUserDtoListEntity.add(santaUserEntity);
        }

    }

}