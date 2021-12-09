package com.tomsproject.secret_santa.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {


    long userid;
    String phoneNumber;
    boolean isUserComplete;
    String token;
    Admin admin;


    public boolean isValidForCreate(){
        return
                this!=null&&
                this.userid == 0 &&
                !this.phoneNumber.isEmpty();
    }

}
