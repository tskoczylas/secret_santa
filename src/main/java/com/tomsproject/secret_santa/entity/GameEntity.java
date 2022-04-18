package com.tomsproject.secret_santa.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank
    @NotNull
    private String gameName;
    private LocalDateTime startDate;
    @NotBlank
    @NotNull
    private LocalDateTime lastResponseDate;
    @NotBlank
    @NotNull
    private String userText;
    @NotBlank
    @NotNull
    private boolean isStartNow;
    private boolean isFirstMessageSent;
    private boolean isSecondMessageSent;
    @NotBlank
    @NotNull
    private boolean gameCompleted;

    @ManyToOne(cascade = CascadeType.REFRESH , targetEntity = Admin.class,fetch = FetchType.LAZY)
    private Admin adminDto;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "gameDto", fetch = FetchType.LAZY)
    private List<SantaUserEntity> userList;

}
