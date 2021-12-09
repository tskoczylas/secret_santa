package com.tomsproject.secret_santa.service;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SantaUserAndAdminServiceTest {


    @Mock
    SantaUserRepo santaUserRepo;
    @InjectMocks
    SantaUserAndAdminService santaUserAndAdminService;




    @Test
    void generateUniqeToken() {
        //given
        //when(santaUserRepo.findAllByToken("12345")).thenReturn(true);
       //when

        //then

        //String uniqeToken = santaUserAndAdminService.generateUniqeToken(5);
    }
}