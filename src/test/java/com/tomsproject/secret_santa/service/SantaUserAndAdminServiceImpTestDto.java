package com.tomsproject.secret_santa.service;

import com.tomsproject.secret_santa.repo.SantaUserRepo;
import com.tomsproject.secret_santa.services.SantaUserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SantaUserAndAdminServiceImpTestDto {


    @Mock
    SantaUserRepo santaUserRepo;
    @InjectMocks
    SantaUserServiceImp santaUserAndAdminService;




    @Test
    void generateUniqeToken() {
        //given
        //when(santaUserRepo.findAllByToken("12345")).thenReturn(true);
       //when

        //then

        //String uniqeToken = santaUserAndAdminService.generateUniqeToken(5);
    }
}