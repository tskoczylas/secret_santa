package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.Game;
import com.tomsproject.secret_santa.model.GameRequest;
import com.tomsproject.secret_santa.model.GameUser;
import com.tomsproject.secret_santa.services.AdminService;
import com.tomsproject.secret_santa.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/game")
public class GameController {


    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping("/admin_games")
    public ResponseEntity<List<Game>> getGames(@RequestBody GameRequest gameRequest){
        System.out.println("inside a adminn games" + gameRequest);

        return gameService.getGamesByAdminComplete(gameRequest.getId(),gameRequest.isComplete());
    }

    @PostMapping("/user_games")
    public ResponseEntity<List<GameUser>> getUserGames(@RequestBody GameRequest gameRequest){

        System.out.println("inside a user games" + gameRequest);


        return gameService.getGamesUsersByGameComplete(gameRequest.getId(),gameRequest.isComplete());
    }


}
