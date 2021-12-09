package com.tomsproject.secret_santa.util;

import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.model.SantaUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DrawnPairs {

public static List<SantaUsersPairDto> lotteryShuffle (List<SantaUsersPairDto> santaUsersPairDtoList){
    List<SantaUserDto> afterShuffleList = santaUsersPairDtoList.stream().map(SantaUsersPairDto::getSantaUserDtoFirst).collect(Collectors.toList());
    List<SantaUserDto> beforeShuffle = new ArrayList<>(afterShuffleList);



    Collections.shuffle(afterShuffleList);
    int  i=0;
    int j =0;
    int k= 0;

    while (!afterShuffleList.isEmpty()){
        if(beforeShuffle.get(i).getUserid()!=afterShuffleList.get(j).getUserid()){
            santaUsersPairDtoList.get(i).setSantaUserDtoSecond(afterShuffleList.get(j));
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

            Random random = new Random();
           int randomUser = random.nextInt(i-1);
            SantaUserDto santaUserToReplaceDto = santaUsersPairDtoList.get(randomUser).getSantaUserDtoSecond();

            if(santaUserToReplaceDto.getUserid()!=beforeShuffle.get(j).getUserid()){
                santaUsersPairDtoList.get(i).setSantaUserDtoSecond(santaUserToReplaceDto);
                santaUsersPairDtoList.get(i).setSantaUserDtoFirst(afterShuffleList.get(j));
                afterShuffleList.remove(afterShuffleList.get(j));
            }

        }


    }
    return santaUsersPairDtoList;

}

}
