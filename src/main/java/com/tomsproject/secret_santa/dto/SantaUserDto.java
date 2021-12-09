package com.tomsproject.secret_santa.dto;

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
    String phoneNumber;
    boolean isUserComplete;
    boolean isUserCreate;
    String token;
    boolean isFirstMessageSendCorrectly;
    boolean isSecondMessageSendCorrectly;
    String giftDesc;
    int chosenPrice;

    public SantaUserDto(boolean isUserCreate) {
        this.isUserCreate = isUserCreate;
    }

    @ManyToOne(cascade = CascadeType.REFRESH , targetEntity = AdminDto.class,fetch = FetchType.EAGER)
    AdminDto adminDto;

    @OneToOne(
            orphanRemoval = false)
    SantaUsersPairDto santaUsersPairDto;


}
