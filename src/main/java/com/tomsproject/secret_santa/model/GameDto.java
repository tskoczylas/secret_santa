package com.tomsproject.secret_santa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GameDto {
    private long gameId;
    private String gameName;
    private LocalDateTime startDate;
    private LocalDateTime lastResponseDate;
    private String userText;
    private boolean gameCompleted;
    private String percentageCompleteUsers;



}
