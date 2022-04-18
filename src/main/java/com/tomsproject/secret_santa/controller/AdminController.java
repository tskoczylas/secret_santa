package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.entity.Admin;
import com.tomsproject.secret_santa.model.AdminDto;
import com.tomsproject.secret_santa.services.SantaAdminService;
import com.tomsproject.secret_santa.util.Tools;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final SantaAdminService santaAdminService;

    @PostMapping("/create")
    public ResponseEntity<String> createAdmin(@RequestBody AdminDto admin) {

        if (!Tools.isValidEmail(admin.getEmail())) {
            log.info("Admin create. Email: " + admin.getEmail() +" unsuccessful - not valid email");
            return ResponseEntity.badRequest().body("The email is not valid");
        }
        if (santaAdminService.isAdminEmailExist(admin)) {
            log.info("Admin create. Email: " + admin.getEmail() +" unsuccessful - email exist");
            return ResponseEntity.badRequest().body("The email exist in our db");
        } else {
            Admin savedAdmin = santaAdminService.saveAdmin(admin);
            log.info("Admin id: " + savedAdmin.getAdminId() + ", email: " + savedAdmin.getEmail() + "has been created.");
            return ResponseEntity.ok("Account id: " + savedAdmin.getAdminId() + " has been created.");
        }
    }

    @GetMapping("/confirm/{token}")
    public ResponseEntity<HttpStatus> getAdmin(@PathVariable String token) {

        if (token == null || token.isEmpty()) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        if (santaAdminService.isValidToken(token)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
}
