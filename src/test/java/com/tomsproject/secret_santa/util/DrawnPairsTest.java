package com.tomsproject.secret_santa.util;

import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.entity.SantaUsersPairEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.util.DrawnPairs.lotteryShuffle;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DrawnPairsTest {

    @Test
    @Disabled
    @RepeatedTest(1000)
    void ShouldReturnListOfUserWithShuffleAddedUser() throws NoSuchAlgorithmException {
        //given
        List<SantaUsersPairEntity> santaUsersPairDtos = new ArrayList<>();

        Random random = new Random();
        final int randomInt = random.nextInt(10000);
        int nextRandomInt = random.nextInt(1000);


        for (int i = randomInt; i <nextRandomInt ; i++) {
           SantaUsersPairEntity santaUsersPairDto = new SantaUsersPairEntity();
           santaUsersPairDto.setId(i);
           SantaUserEntity santaUserEntity = new SantaUserEntity();
           santaUserEntity.setUserid(i);
           santaUsersPairDto.setSantaUserEntityFirst(santaUserEntity);

            santaUsersPairDtos.add(santaUsersPairDto);

        }

        //when
        List<SantaUserEntity> listBeforeShuffleAfterLoopDto = (santaUsersPairDtos).stream().map(SantaUsersPairEntity::getSantaUserEntityFirst).collect(Collectors.toList());
        List<SantaUserEntity> listAfterShuffleDto = lotteryShuffle(santaUsersPairDtos).stream().map(SantaUsersPairEntity::getSantaUserEntitySecond).collect(Collectors.toList());
        //then
        assertThat(listAfterShuffleDto,hasSize(listBeforeShuffleAfterLoopDto.size()));


        for (int i = 0; i < listBeforeShuffleAfterLoopDto.size(); i++) {
            assertNotEquals(listBeforeShuffleAfterLoopDto.get(i).getUserid(),listAfterShuffleDto.get(i).getUserid());
            assertNotNull(listAfterShuffleDto.get(i));
        }





    }

}