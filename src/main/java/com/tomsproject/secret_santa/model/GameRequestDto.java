package com.tomsproject.secret_santa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GameRequestDto {

    private String id;
    private boolean isComplete ;

}
