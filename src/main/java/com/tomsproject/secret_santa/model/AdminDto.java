package com.tomsproject.secret_santa.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminDto {

    private long adminId;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private boolean isActive;
    private String emailConformationSentId;

}








