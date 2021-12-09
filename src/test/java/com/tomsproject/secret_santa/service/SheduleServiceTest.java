package com.tomsproject.secret_santa.service;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SheduleServiceTest {
    @Mock
    AdminRepo adminRepo;
    @Mock
    SantaUserPairRepo santaUserPairRepo;
    @InjectMocks
    SheduleService sheduleService;

    @Captor
    ArgumentCaptor<AdminDto> adminDtoArgumentCaptor;

    @Captor
    ArgumentCaptor<SantaUsersPairDto> santaUsersPairDtoArgumentCaptor;
    /**
    @Test
    public void findCompletedUsersAdminAndRunLottery() {

        //given
        AdminDto adminDto = new AdminDto();


        List<SantaUserDto> listBeforeShuffle = new ArrayList<>();

        for (int i = 0; i <5 ; i++) {
            SantaUserDto santaUserDto = new SantaUserDto();
            SantaUsersPairDto santaUsersPairDto = new SantaUsersPairDto();
           santaUsersPairDto.setSantaUserDtoFirst(santaUserDto);
            santaUserDto.setSantaUsersPairDto(santaUsersPairDto);
            santaUserDto.setUserid(i);
            listBeforeShuffle.add(santaUserDto);

        }
        //when
        adminDto.setAdminId(1);
        adminDto.setAllUsersAdded(true);
        adminDto.setUserList(listBeforeShuffle);
        Optional<AdminDto> optionalAdminDto = Optional.of(adminDto);

       // when(adminRepo.findAllByAllUsersAddedTrueAndItHasBeenDrawnFalse()).thenReturn(optionalAdminDto);
     //   sheduleService.findCompletedUsersAdminAndRunLottery();

        //then

        verify(santaUserPairRepo).save(santaUsersPairDtoArgumentCaptor.capture());

        System.out.println(santaUsersPairDtoArgumentCaptor.getValue());


    }
    **/
    }
