package com.tomsproject.secret_santa.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tomsproject.secret_santa.util.Tools.isValidEmail;
import static org.junit.jupiter.api.Assertions.*;

class ToolsTest {

    String validEmail = "skoczylas@skoczylas.pl";
    List<String> noValidEmails = List.of("noValid", "pl.pl", "smaple@wp", "@wp.pl");

    @Test
    void isValidEmailShouldReturnTrueWhenEmailIsValid() {
        assertTrue(isValidEmail(validEmail));
    }

    @Test
    void isValidEmailShouldReturnFalseWhenAnyEmailIsInvalid() {
        noValidEmails.forEach(noValidEmail -> {
            assertFalse(isValidEmail(noValidEmail));
        });
    }

}
