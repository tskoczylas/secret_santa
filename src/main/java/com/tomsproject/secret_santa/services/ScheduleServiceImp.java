package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.entity.SantaUsersPairEntity;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.util.DrawnPairs.lotteryShuffle;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduleServiceImp implements ScheduleService {

    @Value("${schedule.timeout")
    private String scheduleTimeout;
    final AdminRepo adminRepo;
    final SantaUserPairRepo santaUserPairRepo;
    final SantaUserRepo santaUserRepo;
    final GameRepo gameRepo;
    final ASESMail asesMail;

    //toDo - implement scheduleTimeout as parameter from application prop

    @Override
    @Scheduled(fixedDelay = 600000)
    public void findCompletedUsersAdminAndRunLottery() {


       gameRepo.getNotCompletedGameAfterLastResponseDate(LocalDateTime.now()).ifPresent(
                game -> {

                    try{
                        List<SantaUserEntity> completedUsersList = game.getUserList().stream().dropWhile(santaUserDto -> !santaUserDto.isUserComplete()).collect(Collectors.toList());
                        if(completedUsersList.size()>1){
                            List<SantaUsersPairEntity> savedPairs =
                                    santaUserPairRepo.saveAll(createPairsAndRunLottery(completedUsersList));

                            for (int i = 0; i < savedPairs.size(); i++) {
                                completedUsersList.get(i).setSantaUsersPairDto(savedPairs.get(i));
                            }
                        }

                        List<SantaUserEntity> mailSentUserDto = asesMail.sendLotteryResult(completedUsersList);
                        santaUserRepo.saveAll(mailSentUserDto);

                        game.setGameCompleted(true);
                        game.setSecondMessageSent(true);
                        gameRepo.save(game);
                    }
                    catch (Exception e){
                       log.error("Error completing a game. Error message:  " + e.getMessage());
                    }

                });




    }

    private List<SantaUsersPairEntity> createPairsAndRunLottery(List<SantaUserEntity> users) throws NoSuchAlgorithmException {
       return  lotteryShuffle(users.
                stream().
                map( santaUserDto ->
                        {
                            SantaUsersPairEntity santaUsersPairEntity = new SantaUsersPairEntity();
                            santaUsersPairEntity.setSantaUserEntityFirst(santaUserDto);
                            return santaUsersPairEntity;
                        }
                ).collect(Collectors.toList()));
    }



    @Override
     @Scheduled(fixedDelay = 600000)
    public void sendStartMessage() {
        gameRepo.isAfterStartDate(LocalDateTime.now()).ifPresent(
                gameDto ->
                    gameRepo.save(asesMail.sendLink(gameDto))
        );


    }


}
