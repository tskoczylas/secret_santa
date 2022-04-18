package com.tomsproject.secret_santa.controller;

import com.tomsproject.secret_santa.model.GameDto;
import com.tomsproject.secret_santa.model.GameRequestDto;
import com.tomsproject.secret_santa.model.GameUserDto;
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


    final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping("/admin_games")
    public ResponseEntity<List<GameDto>> getGames(@RequestBody GameRequestDto gameRequestDto){
        return gameService.getGamesByAdminComplete(gameRequestDto.getId(), gameRequestDto.isComplete());
    }

    @PostMapping("/user_games")
    public ResponseEntity<List<GameUserDto>> getUserGames(@RequestBody GameRequestDto gameRequestDto){
        return gameService.getGamesUsersByGameComplete(gameRequestDto.getId(), gameRequestDto.isComplete());
    }


}
