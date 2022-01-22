package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tomsproject.secret_santa.util.DrawnPairs.lotteryShuffle;

@Service
@Log4j2
public class SheduleServiceImp implements    SheduleService {



    AdminRepo adminRepo;
    SantaUserPairRepo santaUserPairRepo;
    SmsService smsService;
    SantaUserRepo santaUserRepo;
    GameRepo gameRepo;
    ASESMail asesMail;


    public SheduleServiceImp(AdminRepo adminRepo, SantaUserPairRepo santaUserPairRepo, SmsService smsService, SantaUserRepo santaUserRepo, GameRepo gameRepo, ASESMail asesMail) {
        this.adminRepo = adminRepo;
        this.santaUserPairRepo = santaUserPairRepo;
        this.smsService = smsService;
        this.santaUserRepo = santaUserRepo;
        this.gameRepo = gameRepo;
        this.asesMail = asesMail;
    }

    @Override
   // @Scheduled(fixedDelay = 1000)
    public void usersCompletedSendSmsAndGenerateToken() {
        /*
        Optional<SantaUserDto> completedUser = santaUserRepo.findSantaUserDtoByIsUserCompleteFalseAndIsUserCreateTrue();

        if(completedUser.isPresent()){







            SantaUserDto savedUserAfterTokenGen = santaUserRepo.save(completedUser.get());

          //  SantaUsersPairDto santaUsersPairDto = new SantaUsersPairDto();
          //  santaUsersPairDto.setSantaUserDtoFirst(savedUserAfterTokenGen);
           // savedUserAfterTokenGen.setSantaUsersPairDto(santaUsersPairDto);

           // SantaUsersPairDto savedSantaUserPairRepo = santaUserPairRepo.save(santaUsersPairDto);


            boolean isSmsSend = smsService.sendRequestSMS(savedUserAfterTokenGen);

            savedUserAfterTokenGen.setFirstMessageSendCorrectly(isSmsSend);
            //savedUserAfterTokenGen.setSantaUsersPairDto(savedSantaUserPairRepo);




                santaUserRepo.save(savedUserAfterTokenGen);

            }

         */
            }








    @Override
    @Scheduled(fixedDelay = 600000)
    public void findCompletedUsersAdminAndRunLottery() {


       gameRepo.getNotCompletedGameAfterLastResponseDate(LocalDateTime.now()).ifPresent(


                game -> {

                    try{
                        List<SantaUserDto> completedUsersList = game.getUserList().stream().dropWhile(santaUserDto -> !santaUserDto.isUserComplete()).collect(Collectors.toList());
                        if(completedUsersList.size()>1){

                            List<SantaUsersPairDto> savedPairs =
                                    santaUserPairRepo.saveAll(createPairsAndRunLottery(completedUsersList));

                            for (int i = 0; i < savedPairs.size(); i++) {
                                completedUsersList.get(i).setSantaUsersPairDto(savedPairs.get(i));
                            }


                        }


                        List<SantaUserDto> mailSentUserDto = asesMail.sendLotteryResult(completedUsersList);
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

    private List<SantaUsersPairDto> createPairsAndRunLottery(List<SantaUserDto> users) {
       return  lotteryShuffle(users.
                stream().
                map((santaUserDto) ->
                        {
                            SantaUsersPairDto santaUsersPairDto = new SantaUsersPairDto();
                            santaUsersPairDto.setSantaUserDtoFirst(santaUserDto);
                            return santaUsersPairDto;
                        }
                ).collect(Collectors.toList()));
    }

    @Override
        public String generateUniqeToken(int tokenBand){
        /*
            String generatedToken = generateToken(tokenBand);
            if (santaUserRepo.findSantaUserDtoByToken(generatedToken).isPresent()) {
                generatedToken = generateToken(tokenBand);
            }
            return generatedToken;

         */
        return null;

        }

    @Override
     @Scheduled(fixedDelay = 600000)
    public void sendStartMessage() {
        gameRepo.isAfterStartDate(LocalDateTime.now()).ifPresent(
                gameDto -> {
                    gameRepo.save(asesMail.sendLink(gameDto));
                }
        );


    }


}
