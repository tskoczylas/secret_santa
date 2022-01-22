package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
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
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.mapper.GameMapper.mapToGameDtoFromCreateUser;
import static com.tomsproject.secret_santa.mapper.GameMapper.mapToGameFromGameDto;
import static com.tomsproject.secret_santa.util.DrawnPairs.lotteryShuffle;

@Service
@Log4j2
public class GameService {

    private SantaUserRepo santaUserRepo;
    private AdminRepo adminRepo;
    private SantaUserPairRepo santaUserPairRepo;
    private SheduleService sheduleService;
    private GameRepo gameRepo;
    private ASESMail asesMail;
    private SantaUserAndAdminService santaUserAndAdminService;

    public GameService(SantaUserRepo santaUserRepo, AdminRepo adminRepo, SantaUserPairRepo santaUserPairRepo, SheduleService sheduleService, GameRepo gameRepo, ASESMail asesMail, SantaUserAndAdminService santaUserAndAdminService) {
        this.santaUserRepo = santaUserRepo;
        this.adminRepo = adminRepo;
        this.santaUserPairRepo = santaUserPairRepo;
        this.sheduleService = sheduleService;
        this.gameRepo = gameRepo;
        this.asesMail = asesMail;
        this.santaUserAndAdminService = santaUserAndAdminService;
    }

    public boolean createGame(CreateUser createUser) {

        AdminDto adminDto = adminRepo.getById(createUser.getAdminId());
        GameDto gameDto = mapToGameDtoFromCreateUser(createUser);
        gameDto.setGameCompleted(false);

        gameDto.setAdminDto(adminDto);



        try {
            if(gameDto.isStartNow()){gameDto.setStartDate(LocalDateTime.now());}
            GameDto savedGame = gameRepo.save(gameDto);
            List<SantaUserDto> createdUserList = santaUserRepo.saveAll(createUsersList(createUser, adminDto,savedGame));
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


                         orElseGet(()->badRequest);}
    catch (Exception e){
         log.error("Exceptions in getLottery: " +  e.getMessage());
         return badRequest;
    }
    }

    public ResponseEntity<List<GameUser>> getGamesUsersByGameComplete(String gameId, boolean isCompleted){
        ResponseEntity<List<GameUser>> badRequest =new ResponseEntity<>(Collections.emptyList(),HttpStatus.BAD_REQUEST);

        try{
            System.out.println(gameRepo.findGamesUserByGameIdAndComplete(Long.valueOf(gameId),isCompleted).get().size());

            return gameRepo.findGamesUserByGameIdAndComplete(Long.valueOf(gameId),isCompleted).
                    map((gameDto -> new ResponseEntity<>
                            ((gameDto.
                                    stream().
                                    map(UserMapper::mapToGameUserFromSantaUserDto)).
                                    collect(Collectors.toList()),HttpStatus.OK))).
                    orElseGet(()->badRequest);}
        catch (Exception e){
            log.error("Exceptions in getLottery: " +  e.getMessage());
            return badRequest;
        }
    }






    private List<SantaUserDto> createUsersList(CreateUser createUser,AdminDto adminDto, GameDto savedGame) {
        List<SantaUserDto> santaUserDtoList=
                createUser.
                        getEmailList().
                        stream().
                        map((email)->
                        { SantaUserDto userDto = new SantaUserDto();
                            userDto.setAdminDto(adminDto);
                            userDto.setEmail(email);
                            userDto.setGameDto(savedGame);
                            userDto.setRoleEnum(RoleEnum.USER);

                            return userDto; }
                            ).
                        collect(Collectors.toList());
        return santaUserDtoList;
    }

    private List<SantaUsersPairDto> santaUsersLottery(List<SantaUserDto> createdUserDtoList){
        return lotteryShuffle(createdUserDtoList.
                stream().
                map((santaUserDto) ->
                        {
                            SantaUsersPairDto santaUsersPairDto = new SantaUsersPairDto();
                            santaUsersPairDto.setSantaUserDtoFirst(santaUserDto);
                            return santaUsersPairDto;
                        }
                ).collect(Collectors.toList()));

    }













        /*
        List<SantaUser> listOfUsers = santaUserRepo.
                findAllByAdminDtoAdminId(adminDto.getAdminId()).
                stream().
                map(UserMapper::mapToSantaUserFromSantaUserDto).
                collect(Collectors.toList());


        return listOfUsers;

         */





}
