package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.TokenUser;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

public interface SantaUserAndAdminService {
    @PostConstruct
    void createHardCodedAdmin();

   TokenUser saveUserResponse(TokenUser tokenUser);


    boolean isCorrectAdmin(long id);




    TokenUser findUserByTokenId(String token);

    Optional<Admin> getAdmin(String adminLogin);

    String countActiveUsers(Long adminId);
}
