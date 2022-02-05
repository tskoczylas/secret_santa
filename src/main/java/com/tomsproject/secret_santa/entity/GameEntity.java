package com.tomsproject.secret_santa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //toDo - token creation - create unique token instead of standard serialize
    private long gameId;
    private String gameName;
    private LocalDateTime startDate;
    private LocalDateTime lastResponseDate;
    private String userText;
    private boolean isStartNow;
    private boolean isFirstMessageSent;
    private boolean isSecondMessageSent;
    private boolean gameCompleted;

    @ManyToOne(cascade = CascadeType.REFRESH , targetEntity = AdminEntity.class,fetch = FetchType.LAZY)
    private AdminEntity adminDto;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "gameDto", fetch = FetchType.LAZY)
    private List<SantaUserEntity> userList;

}
