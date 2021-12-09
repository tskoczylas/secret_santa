package com.tomsproject.secret_santa.model;


import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Admin {

    private long adminId;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private boolean usersAreCompleted;


    int firstChosenPrice;
    int secondChosenPrice;
    int thirdChosenPrice;

    int percentageCompleteUsers;

    public boolean isCorrectAdmin(){
        return this.adminId != 0 &&
                !this.firstName.isEmpty() &&
                !this.secondName.isEmpty() &&
                this.usersAreCompleted;


    }
}


