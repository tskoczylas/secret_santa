package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.TokenUser;
import com.tomsproject.secret_santa.services.AdminService;
import com.tomsproject.secret_santa.services.GameService;
import com.tomsproject.secret_santa.services.SantaUserAndAdminServiceImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
@Log4j2
public class UserController {

    SantaUserAndAdminServiceImp santaUserService;
    GameService gameService;
    AdminService adminService;

    public UserController(SantaUserAndAdminServiceImp santaUserService, GameService gameService, AdminService adminService) {
        this.santaUserService = santaUserService;
        this.gameService = gameService;
        this.adminService = adminService;
    }

    @PostMapping("/user/save")
    public @ResponseBody
    ResponseEntity<TokenUser> post(@RequestBody TokenUser tokenUser) {

        return new ResponseEntity<>(santaUserService.saveUserResponse(tokenUser), HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public @ResponseBody
    HttpStatus adminCreateUser(@RequestBody CreateUser createUser) {
        System.out.println(createUser);
       if (!createUser.isValidForCreate()) return HttpStatus.NOT_ACCEPTABLE;
       else if(!santaUserService.isCorrectAdmin(createUser.getAdminId())) return HttpStatus.NOT_FOUND;
       else if(adminService.isOverActiveGameLimit(createUser.getAdminId())) return HttpStatus.TOO_MANY_REQUESTS;
       else if(!gameService.createGame(createUser)) return HttpStatus.INTERNAL_SERVER_ERROR;

        else return HttpStatus.CREATED; }





    @GetMapping("/santaToken/{tokenId}")
    public @ResponseBody
    ResponseEntity<TokenUser> getUserByToken(@PathVariable String tokenId) {

        return new ResponseEntity<>(santaUserService.
                findUserByTokenId(tokenId), HttpStatus.OK);

    }

    @PostMapping("/admin/create")
    public ResponseEntity<HttpStatus> createAdmin(@RequestBody Admin admin){
        if(!admin.isValidAdminEmail()) return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
       if(adminService.isAdminEmailExist(admin)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else if (adminService.saveAdmin(admin)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/getAdmin/{login}")
    public ResponseEntity<Admin> getAdmin(@PathVariable String login){


        Optional<Admin> getAdmin=santaUserService.getAdmin(login);

        return new ResponseEntity<>( getAdmin.orElse(new Admin()),HttpStatus.OK);
    }


}
