package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.SantaUser;
import com.tomsproject.secret_santa.service.SantaUserAndAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    SantaUserAndAdminService santaUserService;

    public UserController(SantaUserAndAdminService santaUserService) {
        this.santaUserService = santaUserService;
    }

    @PostMapping("api/user/")
    public @ResponseBody
    ResponseEntity<SantaUser> post(@RequestBody SantaUser santaUser) {



        if (santaUser.isComplete() && santaUser != null && santaUserService.isCorrectAdmin(santaUser.getAdmin().getAdminId())) {
            SantaUser santaUserResponse = santaUserService.save(santaUser);

            return new ResponseEntity<>(santaUserResponse, HttpStatus.OK);
        } else return new ResponseEntity<>(new SantaUser(false, 0), HttpStatus.OK);
    }

    @PostMapping("api/create")
    public @ResponseBody
    ResponseEntity<CreateUser> adminCreateUser(@RequestBody CreateUser createUser) {
        if (createUser.isValidForCreate() && santaUserService.isCorrectAdmin(createUser.getAdmin().getAdminId())) {
            CreateUser createUserResponse = santaUserService.createUserGenereteTokenAndSendSMS(createUser);
            return new ResponseEntity<>(createUserResponse, HttpStatus.OK);
        } else return new ResponseEntity<>(new CreateUser(), HttpStatus.NO_CONTENT);


    }

    @GetMapping("api/santaToken/{tokenId}")
    public @ResponseBody
    ResponseEntity<SantaUser> getUserByToken(@PathVariable String tokenId) {

        return new ResponseEntity<>(santaUserService.
                findUserByTokenId(tokenId), HttpStatus.OK);

    }


}
