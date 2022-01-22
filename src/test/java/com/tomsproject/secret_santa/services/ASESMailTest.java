package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "/application.properties")

class ASESMailTest {
     SantaUserDto santaUserDto = new SantaUserDto();

    List<SantaUserDto> userDtoList =List.of(santaUserDto);
    GameDto gameDto = new GameDto();
    AdminDto adminDto = new AdminDto();


    @Test
    void sendLink()  {
        santaUserDto.setUserid(1);
        santaUserDto.setEmail("t.skoczylas1@gmail.com");
        gameDto.setLastResponseDate(LocalDateTime.now());
        gameDto.setUserText("Welcome test");
        adminDto.setFirstName("FirstNameTest");
        adminDto.setSecondName("LastNameTest");
        ASESMail asesMail = new ASESMail();
        asesMail.sendLink(gameDto);
    }
}