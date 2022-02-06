package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.TokenUser;

import java.util.Optional;

public interface SantaUserAndAdminService {


   TokenUser saveUserResponse(TokenUser tokenUser);

    boolean isCorrectAdmin(long id);

    TokenUser findUserByTokenId(String token);

    Optional<Admin> getAdmin(String adminLogin);

    String countActiveUsers(Long adminId);
}
