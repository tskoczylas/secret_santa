package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.model.Game;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.tomsproject.secret_santa.mapper.GameMapper.mapToGameFromGameDto;

@Service
public class UserService {

    SantaUserRepo santaUserRepo;

    public UserService(SantaUserRepo santaUserRepo) {
        this.santaUserRepo = santaUserRepo;
    }



}
