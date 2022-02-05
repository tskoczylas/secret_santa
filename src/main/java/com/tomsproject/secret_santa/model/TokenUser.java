package com.tomsproject.secret_santa.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class TokenUser {

    private  long userid;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  boolean isUserComplete;
    private  boolean isUserCreate;
    private  String percentageCompleteUsers;
    private  long adminId;
    private  String giftDesc;




    public TokenUser(boolean isUserCreate, long userid) {
        this.userid = userid;
        this.isUserCreate = isUserCreate;
    }



    public boolean isValidForSave(){

       return
        this.firstName!=null &&
        this.lastName!=null &&
        this.email!=null &&
        this.giftDesc!=null&&
        !this.firstName.isEmpty()&&
         !this.lastName.isEmpty()&&
          !this.email.isEmpty()&&
                !isUserComplete &&
                !this.giftDesc.isEmpty();
    }

    public boolean isComplete(){
        return
                this.firstName!=null&&
                !this.firstName.isEmpty() &&
                this.lastName!=null&&
                !this.lastName.isEmpty() ;
    }

}
