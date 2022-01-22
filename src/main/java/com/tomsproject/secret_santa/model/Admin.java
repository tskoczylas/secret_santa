package com.tomsproject.secret_santa.model;


import com.tomsproject.secret_santa.enums.RoleEnum;
import lombok.*;

import java.util.Arrays;
import java.util.List;

import static com.tomsproject.secret_santa.util.Tools.isValidEmail;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Admin {

    private long adminId;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    boolean isActive;

    String emailConformationSentId;

   public boolean isValidAdminEmail(){
       return isValidEmail(this.email);
   }
}








