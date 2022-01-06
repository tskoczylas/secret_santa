package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.SantaUser;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

public interface SantaUserAndAdminService {
    @PostConstruct
    void createHardCodedAdmin();

    SantaUser save(SantaUser santaUser);

    void sendTokenSmsAfterUsersCompleted();

    boolean isCorrectAdmin(long id);

    Admin saveAdmin(Admin admin);

    List<SantaUser> createUserGenereteTokenAndSendSMS(CreateUser createUser);

    SantaUser findUserByTokenId(String token);

    Optional<Admin> getAdmin(String adminLogin);
}
