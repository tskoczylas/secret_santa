package com.tomsproject.secret_santa.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mediane {


    public static long getMedian(List<Integer> chosenPrices) {

        if(chosenPrices.size()>2){

            return chosenPrices.
                    stream().
                    collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).
                    entrySet().
                    stream().
                    max(Map.Entry.comparingByValue()).
                    get().
                    getKey();}
        else  return 0;
    }
}