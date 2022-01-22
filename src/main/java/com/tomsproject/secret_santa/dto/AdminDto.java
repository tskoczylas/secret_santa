package com.tomsproject.secret_santa.dto;

import com.tomsproject.secret_santa.enums.RoleEnum;
import com.tomsproject.secret_santa.repo.AdminRepo;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long adminId;

    @NotNull
    @NotBlank
    String firstName;

    @NotNull
    @NotBlank
    String secondName;

    @NotNull
    @NotBlank
    String email;

    @NotNull
    @NotBlank
    String password;

    boolean isActive;
    String activationToken;

    String emailConformationSentId;


    @Enumerated(EnumType.ORDINAL)
    RoleEnum roleEnum;

    @OneToMany(mappedBy = "adminDto")
    List<GameDto> gameDtoList;




}
