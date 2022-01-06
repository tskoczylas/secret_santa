package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.SantaUser;
import com.tomsproject.secret_santa.services.SantaUserAndAdminServiceImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@Log4j2
public class UserController {

    SantaUserAndAdminServiceImp santaUserService;

    public UserController(SantaUserAndAdminServiceImp santaUserService) {
        this.santaUserService = santaUserService;
    }

    @PostMapping("/user/")
    public @ResponseBody
    ResponseEntity<SantaUser> post(@RequestBody SantaUser santaUser) {



        if (santaUser.isComplete() && santaUser != null && santaUserService.isCorrectAdmin(santaUser.getAdmin().getAdminId())) {
            SantaUser santaUserResponse = santaUserService.save(santaUser);

            return new ResponseEntity<>(santaUserResponse, HttpStatus.OK);
        } else return new ResponseEntity<>(new SantaUser(false, 0), HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public @ResponseBody
    ResponseEntity<List<SantaUser>> adminCreateUser(@RequestBody CreateUser createUser) {
       log.info("inside of post /user/create "+ createUser);
       // if (createUser.isValidForCreate() && santaUserService.isCorrectAdmin(createUser.getAdmin().getAdminId())) {
          //  List<SantaUser> userGenereteTokenAndSendSMS = santaUserService.createUserGenereteTokenAndSendSMS(createUser);

        System.out.println(createUser);
            //return new ResponseEntity<>(userGenereteTokenAndSendSMS, HttpStatus.OK);
         return new ResponseEntity<>( List.of(new SantaUser()), HttpStatus.NO_CONTENT);


    }

    @GetMapping("/santaToken/{tokenId}")
    public @ResponseBody
    ResponseEntity<SantaUser> getUserByToken(@PathVariable String tokenId) {

        return new ResponseEntity<>(santaUserService.
                findUserByTokenId(tokenId), HttpStatus.OK);

    }

    @PostMapping("/createAdmin")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){

        Admin responseAdmin = new Admin();

        if(admin.isAdminCreatable()){
             responseAdmin = santaUserService.saveAdmin(admin);
        }

        return new ResponseEntity<>(responseAdmin,HttpStatus.OK);
    }

    @GetMapping("/getAdmin/{login}")
    public ResponseEntity<Admin> getAdmin(@PathVariable String login){

        Optional<Admin> getAdmin=santaUserService.getAdmin(login);

        return new ResponseEntity<>( getAdmin.orElse(new Admin()),HttpStatus.OK);
    }


}
