package com.tomsproject.secret_santa.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //toDo - token creation
    private long gameId;
    private String gameName;
    private LocalDateTime startDate;
    private LocalDateTime lastResponseDate;
    private String userText;
    private boolean isStartNow;
    private boolean isFirstMessageSent;
    private boolean isSecondMessageSent;





    private boolean gameCompleted;

    @ManyToOne(cascade = CascadeType.REFRESH , targetEntity = AdminDto.class,fetch = FetchType.LAZY)
    AdminDto adminDto;



    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "gameDto", fetch = FetchType.LAZY)
     List<SantaUserDto> userList;

}
