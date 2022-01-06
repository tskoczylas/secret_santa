package com.tomsproject.secret_santa.model;


import com.tomsproject.secret_santa.enums.RoleEnum;
import lombok.*;

import java.util.Arrays;
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

    RoleEnum roleEnum;

    String email;



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

    public boolean isAdminCreatable(){
        return this.adminId == 0 &&
                !this.firstName.isEmpty() &&
                !this.secondName.isEmpty() &&
                !this.login.isEmpty()&&
                !this.password.isEmpty()&&
                !this.usersAreCompleted &&
                this.firstChosenPrice!=0&&
                this.secondChosenPrice!=0&&
                this.thirdChosenPrice!=0&&
                this.roleEnum!=null&&
                Arrays.stream(RoleEnum.values()).
                        anyMatch(v->v.
                                name().
                                equals(this.roleEnum.name()));



    }




}


