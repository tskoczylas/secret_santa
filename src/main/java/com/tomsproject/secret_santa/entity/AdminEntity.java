package com.tomsproject.secret_santa.entity;

import com.tomsproject.secret_santa.enums.RoleEnum;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long adminId;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String secondName;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private   String password;

    private boolean isActive;
    private  String activationToken;
    private String emailConformationSentId;

    @Enumerated(EnumType.ORDINAL)
    private RoleEnum roleEnum;

    @OneToMany(mappedBy = "adminDto")
    private List<GameEntity> gameDtoList;




}
