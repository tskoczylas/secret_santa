package com.tomsproject.secret_santa.util;

import org.junit.jupiter.api.Test;

import static com.tomsproject.secret_santa.util.HostUtils.generateToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HostUtilsTest {

    @Test
    public void  generateTokenShouldReturnDigitToken(){
        //given
        int band = 5;
        //when
        String token = generateToken(band);
        //then
        assertEquals(token.length(),band);


    }

}