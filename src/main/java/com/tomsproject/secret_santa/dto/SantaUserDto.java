package com.tomsproject.secret_santa.dto;

import com.tomsproject.secret_santa.enums.RoleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class SantaUserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long userid;
    String firstName;
    String lastName;
    boolean isUserComplete;
    private String startMessageSentId;
    private String lastMessageSentId;
    String giftDesc;
    String email;

    @Enumerated(value = EnumType.STRING)
    RoleEnum roleEnum;

    @ManyToOne(fetch = FetchType.LAZY)
    GameDto gameDto;

    @ManyToOne(fetch = FetchType.LAZY)
    AdminDto adminDto;




    @OneToOne(fetch = FetchType.EAGER,
            orphanRemoval = false)
    SantaUsersPairDto santaUsersPairDto;


}
