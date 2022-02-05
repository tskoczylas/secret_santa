package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.entity.AdminEntity;
import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "/application.properties")

class ASESMailTest {
     SantaUserEntity santaUserEntity = new SantaUserEntity();

    List<SantaUserEntity> userDtoList =List.of(santaUserEntity);
    GameEntity gameDto = new GameEntity();
    AdminEntity adminDto = new AdminEntity();


    @Test
    @Disabled
    void sendLink()  {
        santaUserEntity.setUserid(1);
        santaUserEntity.setEmail("t.skoczylas1@gmail.com");
        gameDto.setLastResponseDate(LocalDateTime.now());
        gameDto.setUserText("Welcome test");
        adminDto.setFirstName("FirstNameTest");
        adminDto.setSecondName("LastNameTest");
        ASESMail asesMail = new ASESMail();
        //asesMail.sendLink(gameDto);
    }
}