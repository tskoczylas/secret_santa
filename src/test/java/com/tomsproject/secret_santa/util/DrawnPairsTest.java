package com.tomsproject.secret_santa.util;

import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.model.SantaUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.util.DrawnPairs.lotteryShuffle;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;

class DrawnPairsTest {

    @Test
    @Disabled
    @RepeatedTest(1000)
    void ShouldReturnListOfUserWithShuffleAddedUser() {
        //given
        List<SantaUsersPairDto> santaUsersPairDtos = new ArrayList<>();

        Random random = new Random();
        final int randomInt = random.nextInt(10000);
        int nextRandomInt = random.nextInt(1000);


        for (int i = randomInt; i <nextRandomInt ; i++) {
           SantaUsersPairDto santaUsersPairDto = new SantaUsersPairDto();
           santaUsersPairDto.setId(i);
           SantaUserDto santaUserDto = new SantaUserDto();
           santaUserDto.setUserid(i);
           santaUsersPairDto.setSantaUserDtoFirst(santaUserDto);

            santaUsersPairDtos.add(santaUsersPairDto);

        }

        //when
        List<SantaUserDto> listBeforeShuffleAfterLoopDto = (santaUsersPairDtos).stream().map(SantaUsersPairDto::getSantaUserDtoFirst).collect(Collectors.toList());
        List<SantaUserDto> listAfterShuffleDto = lotteryShuffle(santaUsersPairDtos).stream().map(SantaUsersPairDto::getSantaUserDtoSecond).collect(Collectors.toList());
        //then
        assertThat(listAfterShuffleDto,hasSize(listBeforeShuffleAfterLoopDto.size()));


        for (int i = 0; i < listBeforeShuffleAfterLoopDto.size(); i++) {
            assertNotEquals(listBeforeShuffleAfterLoopDto.get(i).getUserid(),listAfterShuffleDto.get(i).getUserid());
            assertNotNull(listAfterShuffleDto.get(i));
        }





    }

}