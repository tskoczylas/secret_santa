package com.tomsproject.secret_santa.util;

import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.entity.SantaUsersPairEntity;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DrawnPairs {

    private DrawnPairs() {
    }

    public static List<SantaUsersPairEntity> lotteryShuffle (List<SantaUsersPairEntity> santaUsersPairDtoList) throws NoSuchAlgorithmException {

    List<SantaUserEntity> afterShuffleList = santaUsersPairDtoList.stream().map(SantaUsersPairEntity::getSantaUserEntityFirst).collect(Collectors.toList());


    List<SantaUserEntity> beforeShuffle = new ArrayList<>(afterShuffleList);



    Collections.shuffle(afterShuffleList);
    int  i=0;
    int j =0;
    int k= 0;

    while (!afterShuffleList.isEmpty()){
        if(beforeShuffle.get(i).getUserid()!=afterShuffleList.get(j).getUserid()){
            santaUsersPairDtoList.get(i).setSantaUserEntitySecond(afterShuffleList.get(j));
            afterShuffleList.remove(afterShuffleList.get(j));
            i++;
            j=0;
            k=0;
        }
        else if(k<10) {
            if(j<afterShuffleList.size()-1) {j++;}
            k++;
        }
        else  {

            Random random = SecureRandom.getInstanceStrong();
           int randomUser = random.nextInt(i-1);
            SantaUserEntity santaUserToReplaceDto = santaUsersPairDtoList.get(randomUser).getSantaUserEntitySecond();

            if(santaUserToReplaceDto.getUserid()!=beforeShuffle.get(j).getUserid()){
                santaUsersPairDtoList.get(i).setSantaUserEntitySecond(santaUserToReplaceDto);
                santaUsersPairDtoList.get(i).setSantaUserEntityFirst(afterShuffleList.get(j));
                afterShuffleList.remove(afterShuffleList.get(j));
            }

        }


    }
    return santaUsersPairDtoList;

}

}
