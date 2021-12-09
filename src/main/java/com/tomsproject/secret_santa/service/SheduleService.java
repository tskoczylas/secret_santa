package com.tomsproject.secret_santa.service;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.util.DrawnPairs.lotteryShuffle;
import static com.tomsproject.secret_santa.util.HostUtils.generateToken;
import static com.tomsproject.secret_santa.util.Mediane.getMedian;

@Service
public class SheduleService {

   @Value("${token.band}")
    private String tokenBand;

    AdminRepo adminRepo;
    SantaUserPairRepo santaUserPairRepo;
    SmsService smsService;
    SantaUserRepo santaUserRepo;

    public SheduleService(AdminRepo adminRepo, SantaUserPairRepo santaUserPairRepo, SmsService smsService, SantaUserRepo santaUserRepo) {
        this.adminRepo = adminRepo;
        this.santaUserPairRepo = santaUserPairRepo;
        this.smsService = smsService;
        this.santaUserRepo = santaUserRepo;
    }

    @Scheduled(fixedDelay = 1000)
    public void usersCompletedSendSmsAndGenerateToken() {
        Optional<SantaUserDto> completedUser = santaUserRepo.findSantaUserDtoByIsUserCompleteFalseAndIsUserCreateTrue();

        if(completedUser.isPresent()){



            completedUser.get().setToken(generateUniqeToken(Integer.parseInt(tokenBand)));




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
            }








    public void findCompletedUsersAdminAndRunLottery(long  adminId) {


        adminRepo.findAdminDtoByAdminIdAndItHasBeenDrawnIsFalse(adminId).ifPresent(
                users -> {
                    List<SantaUsersPairDto> santaUsersPairDtoList =
                            users.

                                    getUserList().
                                    stream().
                                    map(SantaUserDto::getSantaUsersPairDto).
                                    collect(Collectors.toList());

                    List<Integer> chosenPriceList = santaUsersPairDtoList.stream().map(SantaUsersPairDto::getSantaUserDtoFirst).map(SantaUserDto::getChosenPrice).collect(Collectors.toList());
                    long medianChosenPrice = getMedian(chosenPriceList);



                    List<SantaUsersPairDto> santaUsersPairDtoAfterLottery =
                            lotteryShuffle(santaUsersPairDtoList);

                        AdminDto adminDto =santaUsersPairDtoAfterLottery.get(0).getSantaUserDtoFirst().getAdminDto();
                        adminDto.setItHasBeenDrawn(true);
                        adminDto.setMedianeUserChosenPrice(medianChosenPrice);
                        adminRepo.save(adminDto);





                    List<SantaUsersPairDto> santaUsersPairDtosAfter = santaUserPairRepo.saveAll(santaUsersPairDtoAfterLottery);

                smsService.sendLotteryFinalSms(santaUsersPairDtosAfter);
                });}


        public String generateUniqeToken ( int tokenBand){
            String generatedToken = generateToken(tokenBand);
            if (santaUserRepo.findSantaUserDtoByToken(generatedToken).isPresent()) {
                generatedToken = generateToken(tokenBand);
            }
            return generatedToken;


        }


    }
