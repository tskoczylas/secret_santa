package com.tomsproject.secret_santa.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SantaUsersPairDto {

    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne()
    SantaUserDto santaUserDtoFirst;
    @ManyToOne()
    SantaUserDto santaUserDtoSecond;



}
