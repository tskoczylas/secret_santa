package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.repo.SantaUserRepo;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    SantaUserRepo santaUserRepo;

    public UserService(SantaUserRepo santaUserRepo) {
        this.santaUserRepo = santaUserRepo;
    }



}
