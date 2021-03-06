package com.tomsproject.secret_santa.service;

import com.tomsproject.secret_santa.entity.Admin;
import com.tomsproject.secret_santa.entity.SantaUsersPairEntity;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.services.ScheduleService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SheduleServiceTest {
    @Mock
    AdminRepo adminRepo;
    @Mock
    SantaUserPairRepo santaUserPairRepo;
    @InjectMocks
    ScheduleService scheduleService;

    @Captor
    ArgumentCaptor<Admin> adminDtoArgumentCaptor;

    @Captor
    ArgumentCaptor<SantaUsersPairEntity> santaUsersPairDtoArgumentCaptor;
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
