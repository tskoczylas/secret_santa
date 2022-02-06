package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.repo.SantaUserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

   final SantaUserRepo santaUserRepo;




}
