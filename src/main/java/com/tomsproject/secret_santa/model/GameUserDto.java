package com.tomsproject.secret_santa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GameUserDto {

    private long userid;
    private  String firstName;
    private  String lastName;
    private  boolean isUserComplete;
    private  String email;
}
