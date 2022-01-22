package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.TokenUser;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SantaUserAndAdminServiceImpTest {

@Mock
private SantaUserRepo santaUserRepo;
@Mock
    private AdminRepo adminRepo;
@Mock
    private SantaUserPairRepo santaUserPairRepo;
@Mock
    private SmsService smsService;
@Mock
    private SheduleService sheduleService;
@Mock
    private PasswordEncoder passwordEncoder;
@Mock
    private GameRepo gameRepo;


@InjectMocks
SantaUserAndAdminServiceImp santaUserAndAdminServiceImp;



    @Test
    void findUserByTokenIdAndCreateProtUserCompleted() {
        //given
        String token = "1234";
        SantaUserDto santaUserDto = new SantaUserDto();
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(1);
      //  santaUserDto.setToken(token);

        long completedUsers=1;
        long usersCreated  =0;

       // santaUserDto.setAdminDto(adminDto);

        //when
       // when(santaUserRepo.findSantaUserDtoByToken(token)).thenReturn(Optional.of(santaUserDto));
       // when(santaUserRepo.countUsersCompletedByAdminId(1)).thenReturn(completedUsers);
       // when(santaUserRepo.countUsersCreatedByAdminID(1)).thenReturn(usersCreated);

        TokenUser userByTokenId = santaUserAndAdminServiceImp.findUserByTokenId(token);

        //then
        System.out.println(userByTokenId);
        //assertEquals(userByTokenId.getAdmin().getPercentageCompleteUsers(),50);








      //then

    }

    @Test
    void countActiveUsersShouldCountWhenUserCreateNotZero() {
        //given
         when(santaUserRepo.countUsersCompletedByAdminId(1)).thenReturn(2L);
         when(santaUserRepo.countUsersCreatedByAdminID(1)).thenReturn(4L);
         //when
        String res = santaUserAndAdminServiceImp.countActiveUsers(1L);
        //then
        assertEquals(res,"50");

    }
        @Test
    void countActiveUsersShouldNotCountWhenUserCreateIsZero() {
        //given
        when(santaUserRepo.countUsersCompletedByAdminId(1)).thenReturn(2L);
        when(santaUserRepo.countUsersCreatedByAdminID(1)).thenReturn(0L);
        //when
        String res = santaUserAndAdminServiceImp.countActiveUsers(1L);
        //then
        assertEquals(res,"0");

    }

}