package com.tomsproject.secret_santa.util;

public class Tools {

    private Tools() {
    }

    public   static boolean isValidEmail(String email){
        String regExp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.toLowerCase().matches(regExp);
    }
}
