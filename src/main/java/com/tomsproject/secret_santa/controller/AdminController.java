package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.services.AdminService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/admin")
@Log4j2
public class AdminController {



    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }



    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createAdmin(@RequestBody Admin admin){



        if(!admin.isValidAdminEmail()) return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        if(adminService.isAdminEmailExist(admin)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else if (adminService.saveAdmin(admin)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/confirm/{token}")

    public ResponseEntity<HttpStatus> getAdmin(@PathVariable String token){


        if(token==null || token.isEmpty()) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        if(adminService.isValidToken(token)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }



}
