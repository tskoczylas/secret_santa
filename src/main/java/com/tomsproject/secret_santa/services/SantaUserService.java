package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.model.AdminDto;
import com.tomsproject.secret_santa.model.TokenUserDto;

import java.util.Optional;

public interface SantaUserService {


   TokenUserDto saveUserResponse(TokenUserDto tokenUserDto);

    boolean isCorrectAdmin(long id);

    TokenUserDto findUserByTokenId(String token);

    Optional<AdminDto> getAdmin(String adminLogin);

    String countActiveUsers(Long adminId);
}
