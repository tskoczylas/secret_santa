package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.entity.AdminEntity;
import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.enums.RoleEnum;
import com.tomsproject.secret_santa.mapper.GameMapper;
import com.tomsproject.secret_santa.mapper.UserMapper;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.Game;
import com.tomsproject.secret_santa.model.GameUser;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.mapper.GameMapper.mapToGameDtoFromCreateUser;

@Service
@Log4j2
@RequiredArgsConstructor
public class GameService {

    final SantaUserRepo santaUserRepo;
    final AdminRepo adminRepo;
    final GameRepo gameRepo;
    final ASESMail asesMail;
    final SantaUserAndAdminService santaUserAndAdminService;



    public boolean createGame(CreateUser createUser) {

        AdminEntity adminDto = adminRepo.getById(createUser.getAdminId());
        GameEntity gameDto = mapToGameDtoFromCreateUser(createUser);
        gameDto.setGameCompleted(false);

        gameDto.setAdminDto(adminDto);



        try {
            if(gameDto.isStartNow()){gameDto.setStartDate(LocalDateTime.now());}
            GameEntity savedGame = gameRepo.save(gameDto);
            List<SantaUserEntity> createdUserList = santaUserRepo.saveAll(createUsersList(createUser, adminDto,savedGame));
            savedGame.setAdminDto(adminDto);
            savedGame.setUserList(createdUserList);

           if(gameDto.isStartNow()) {gameRepo.save(asesMail.sendLink(savedGame));}



            return true;

        }
        catch (Exception exception){
            log.error("error inside a createGame "  + exception.getMessage());
            return false;
        }

    }



    public ResponseEntity<List<Game>> getGamesByAdminComplete(String adminId, boolean isCompleted){

        ResponseEntity<List<Game>> badRequest =new ResponseEntity<>(Collections.emptyList(),HttpStatus.BAD_REQUEST);


        try{
         return


                 gameRepo.findGamesByAdminAndComplete(Long.valueOf(adminId),isCompleted).

                 map((gameDt -> new ResponseEntity<>(
                         (
                                 gameDt.
                         stream().

                         map(GameMapper::mapToGameFromGameDto)).
                     peek(game -> game.setPercentageCompleteUsers
                             (santaUserAndAdminService.countActiveUsers(game.getGameId()))).

                         collect(Collectors.toList())


                         ,HttpStatus.OK))).


                         orElse(badRequest);}
    catch (Exception e){
         log.error("Exceptions in getLottery: " +  e.getMessage());
         return badRequest;
    }
    }

    public ResponseEntity<List<GameUser>> getGamesUsersByGameComplete(String gameId, boolean isCompleted){
        ResponseEntity<List<GameUser>> badRequest =new ResponseEntity<>(Collections.emptyList(),HttpStatus.BAD_REQUEST);

        try{

            return gameRepo.findGamesUserByGameIdAndComplete(Long.valueOf(gameId),isCompleted).
                    map((gameDto -> new ResponseEntity<>
                            ((gameDto.
                                    stream().
                                    map(UserMapper::mapToGameUserFromSantaUserDto)).
                                    collect(Collectors.toList()),HttpStatus.OK))).
                    orElse(badRequest);}
        catch (Exception e){
            log.error("Exceptions in getLottery: " +  e.getMessage());
            return badRequest;
        }
    }






    private List<SantaUserEntity> createUsersList(CreateUser createUser, AdminEntity adminDto, GameEntity savedGame) {
        return
                createUser.
                        getEmailList().
                        stream().
                        map( email->
                        { SantaUserEntity userDto = new SantaUserEntity();
                            userDto.setAdminDto(adminDto);
                            userDto.setEmail(email);
                            userDto.setGameDto(savedGame);
                            userDto.setRoleEnum(RoleEnum.USER);

                            return userDto; }
                            ).
                        collect(Collectors.toList());
    }







}
