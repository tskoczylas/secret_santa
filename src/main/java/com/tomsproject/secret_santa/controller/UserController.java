package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.model.AdminDto;
import com.tomsproject.secret_santa.model.CreateUserDto;
import com.tomsproject.secret_santa.model.TokenUserDto;
import com.tomsproject.secret_santa.services.SantaAdminService;
import com.tomsproject.secret_santa.services.GameService;
import com.tomsproject.secret_santa.services.SantaUserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    final SantaUserServiceImp santaUserService;
    final GameService gameService;
    final SantaAdminService santaAdminService;

    @PostMapping("/user/save")
    public @ResponseBody
    ResponseEntity<TokenUserDto> post(@RequestBody TokenUserDto tokenUserDto) {

        return new ResponseEntity<>(santaUserService.saveUserResponse(tokenUserDto), HttpStatus.OK);
    }

    @PostMapping("/user/create")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    HttpStatus createGame(@RequestBody CreateUserDto createUserDto) {
       if (!createUserDto.isValidForCreate()) return HttpStatus.NOT_ACCEPTABLE;
       else if(!santaUserService.isCorrectAdmin(createUserDto.getAdminId())) return HttpStatus.NOT_FOUND;
       else if(santaAdminService.isOverActiveGameLimit(createUserDto.getAdminId())) return HttpStatus.TOO_MANY_REQUESTS;
       else if(!gameService.createGame(createUserDto)) return HttpStatus.INTERNAL_SERVER_ERROR;

        else return HttpStatus.CREATED; }





    @GetMapping("/santaToken/{tokenId}")
    public @ResponseBody
    ResponseEntity<TokenUserDto> getUserByToken(@PathVariable String tokenId) {

        return new ResponseEntity<>(santaUserService.
                findUserByTokenId(tokenId), HttpStatus.OK);

    }



    @GetMapping("/getAdmin/{login}")
    public ResponseEntity<AdminDto> getAdmin(@PathVariable String login){


        Optional<AdminDto> getAdmin=santaUserService.getAdmin(login);

        return new ResponseEntity<>( getAdmin.orElse(new AdminDto()),HttpStatus.OK);
    }


}
