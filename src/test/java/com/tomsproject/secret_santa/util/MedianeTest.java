package com.tomsproject.secret_santa.util;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.util.Mediane.getMedian;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MedianeTest {

    @Test
    public  void  getMedianeTest(){
        //given
        List<Integer> longList = List.of(50,50,50);
        //when
        long median = getMedian(longList);
        System.out.println(median);
        //then

    }




}