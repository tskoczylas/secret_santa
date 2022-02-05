package com.tomsproject.secret_santa.model;


import lombok.*;


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
    private boolean isActive;
    private String emailConformationSentId;

   public boolean isValidAdminEmail(){
       return isValidEmail(this.email);
   }
}








