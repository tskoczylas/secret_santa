package com.tomsproject.secret_santa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SantaUsersPairEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @ManyToOne()
    private  SantaUserEntity santaUserEntityFirst;
    @ManyToOne()
    private  SantaUserEntity santaUserEntitySecond;



}
