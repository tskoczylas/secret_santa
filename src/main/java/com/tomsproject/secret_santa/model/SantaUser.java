package com.tomsproject.secret_santa.model;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class SantaUser {

    long userid;
    String firstName;
    String lastName;
    String phoneNumber;
    boolean isUserComplete;
    boolean isUserCreate;
    String token;
    boolean isFirstMessageSendCorrectly;
    boolean isSecondMessageSendCorrectly;
   Admin admin;
    String giftDesc;
    int chosenPrice;
    String email;


    public SantaUser(boolean isUserCreate,long userid) {
        this.userid = userid;
        this.isUserCreate = isUserCreate;
    }

    public boolean isComplete(){
        return
                this.firstName!=null&&
                !this.firstName.isEmpty() &&
                this.lastName!=null&&
                !this.lastName.isEmpty() &&
                this.phoneNumber!=null&&
                !this.phoneNumber.isEmpty();
    }

}
