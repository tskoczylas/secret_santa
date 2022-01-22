package com.tomsproject.secret_santa.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreateUserTest {


    List<String>  validEmailList= List.of("sample@valid.com","sampple@valid.com");

    CreateUser validUser = new CreateUser(validEmailList, LocalDateTime.now().plusMinutes(45),LocalDateTime.now().plusHours(13),"game", 2L,"sample",false);



    @Test
    void isValidForCreateShouldReturnTrueWhenAllFieldAreValid()  {
        //given

        //when
        //then
          assertTrue(validUser.isValidForCreate());
    }


    @Test
    void isValidForCreateShouldReturnFalseWhenEmailListSizeLessThan2()  {
        //given
        List<String> nonValidEmailList =  List.of("sample@valid.com");
        //when
        validUser.setEmailList(nonValidEmailList);
        //then
        assertFalse(validUser.isValidForCreate());
    }



    @Test
    void isValidForCreateShouldReturnFalseWhenAnyFieldIsNull()  {
        //given
        CreateUser createUser1 = new CreateUser();
        validUser.setAdminId(null);

        //when
        //then
        assertFalse(validUser.isValidForCreate());
    }
    @Test
    void isValidForCreateShouldReturnFalseWhenAnyFieldIsNullBis()  {
        //given
        CreateUser createUser1 = new CreateUser();
        validUser.setEmailList(null);

        //when
        //then
        assertFalse(validUser.isValidForCreate());
    }

    @Test
    void isValidEmailShouldValidateEmail() {
        //given
        CreateUser createUser = new CreateUser();
        String validEmail="sample@sample.com";
       String nonValidEmail="saml@@.c";
       //when
        boolean validEmailCheck = createUser.isValidEmail(validEmail);
        boolean nonValidEmailCheck = createUser.isValidEmail(nonValidEmail);
        //then
        assertTrue(validEmailCheck);
        assertFalse(nonValidEmailCheck);



    }

    @Test
    void isStartDateValid() {
        //given
        LocalDateTime setDateTime = LocalDateTime.now().plusHours(2);
        //when
        validUser.setStartNow(false);
        validUser.setStartDate(setDateTime);

        //then
        assertTrue( validUser.isStartDateValid());
    }

    @Test
    void isStartDateNotValid() {
        //given
        LocalDateTime setDateTime = LocalDateTime.now().minusMinutes(1);
        //when
        validUser.setStartNow(false);
        validUser.setStartDate(setDateTime);
        //then
        assertFalse( validUser.isStartDateValid());
    }

    @Test
    void isLastResponseDateValid() {
        //given
        LocalDateTime setDateTime = LocalDateTime.now().plusHours(validUser.getLastResponseTimeout()+1);
        //when
        validUser.setLastResponseDate(setDateTime);
        //then
        assertTrue( validUser.isLastResponseDateValid());
    }

    @Test
    void isLastResponseDateNotValid() {
        //given
        LocalDateTime setDateTime = LocalDateTime.now().plusHours(validUser.getLastResponseTimeout());
        //when
        validUser.setLastResponseDate(setDateTime);
        //then
        assertFalse( validUser.isLastResponseDateValid());
    }
}