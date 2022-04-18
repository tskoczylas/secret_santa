package com.tomsproject.secret_santa.util;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Tools {

    public static boolean isValidEmail(String email) {
        String regExp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.toLowerCase().matches(regExp);
    }

    public static String stringErrorMessage(Exception e) {
        StackTraceElement[] stackTrace = Thread.currentThread()
                .getStackTrace();

        return "Exception: " + e.toString() +
                " in class: " + stackTrace[2].getFileName() +
                " method: " + stackTrace[2].getMethodName() +
                " msg: " + e.getMessage() +
                " localized msg: " + e.getLocalizedMessage();
    }

    public static String stringErrorMessageForLog(Exception e) {
        StackTraceElement[] stackTrace = Thread.currentThread()
                .getStackTrace();

        return "Exception: " + e.toString() +
                " in class: " + stackTrace[2].getFileName() +
                " method: " + stackTrace[2].getMethodName() +
                " msg: " + e.getMessage() +
                " localized msg: " + e.getLocalizedMessage() +
                " stack trace: " + Arrays.stream(e.getStackTrace()).
                                collect(Collectors.toList()).
                                stream().
                                map(s -> s.toString() + "\n").
                                collect(Collectors.toList());
    }
}
