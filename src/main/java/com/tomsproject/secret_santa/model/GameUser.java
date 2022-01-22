package com.tomsproject.secret_santa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GameUser {

    long userid;
    String firstName;
    String lastName;
    boolean isUserComplete;
    String email;
}
