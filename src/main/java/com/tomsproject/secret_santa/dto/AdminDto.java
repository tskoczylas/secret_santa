package com.tomsproject.secret_santa.dto;

import com.tomsproject.secret_santa.model.SantaUser;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long adminId;
    String firstName;
    String secondName;
    String login;
    String password;
    String webToken;
    String santaMessage;
    String smsSource;


    int firstChosenPrice;
    int secondChosenPrice;
    int thirdChosenPrice;

    long medianeUserChosenPrice;

    boolean allUsersAdded;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "adminDto", orphanRemoval = true, fetch = FetchType.EAGER)
    List<SantaUserDto> userList;

    boolean itHasBeenDrawn;


}
