package com.tomsproject.secret_santa.entity;

import com.tomsproject.secret_santa.enums.RoleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class SantaUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private   long userid;
    private String firstName;
    private String lastName;
    boolean isUserComplete;
    private String startMessageSentId;
    private String lastMessageSentId;
    private String giftDesc;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum roleEnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private GameEntity gameDto;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdminEntity adminDto;


    @OneToOne(fetch = FetchType.EAGER)
    private  SantaUsersPairEntity santaUsersPairDto;


}
