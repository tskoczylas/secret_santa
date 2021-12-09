package com.tomsproject.secret_santa.service;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.SantaUser;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static com.tomsproject.secret_santa.mapper.UserMapper.*;


@Service
public class SantaUserAndAdminService {

    SantaUserRepo santaUserRepo;
    AdminRepo adminRepo;
    SantaUserPairRepo santaUserPairRepo;
    SmsService smsService;
    SheduleService sheduleService;

    public SantaUserAndAdminService(SheduleService sheduleService,
                                    SmsService smsService, SantaUserRepo santaUserRepo, AdminRepo adminRepo, SantaUserPairRepo santaUserPairRepo) {
        this.santaUserRepo = santaUserRepo;
        this.adminRepo = adminRepo;
        this.santaUserPairRepo = santaUserPairRepo;
        this.smsService = smsService;
        this.sheduleService = sheduleService;

    }

    @PostConstruct
    void createHardCodedAdmin() {
        if (!adminRepo.existsById(1L)) {

            AdminDto adminDto = new AdminDto();
            adminDto.setFirstName("Tomasz");
            adminDto.setSecondName("Skoczylas");
            adminDto.setFirstChosenPrice(40);
            adminDto.setSecondChosenPrice(50);
            adminDto.setThirdChosenPrice(60);
            adminRepo.save(adminDto);
        }
    }

    public SantaUser save(SantaUser santaUser) {

        AdminDto adminDto = adminRepo.getById(santaUser.getAdmin().getAdminId());
        SantaUserDto santaUserDto = mapSantaUserDtoFromSantaUSer(santaUser);
        santaUserDto.setAdminDto(adminDto);
        santaUserDto.setUserComplete(true);
        santaUserDto.setUserCreate(true);
        SantaUsersPairDto santaUsersPairDto = new SantaUsersPairDto();
        santaUsersPairDto.setSantaUserDtoFirst(santaUserDto);
        santaUserDto.setSantaUsersPairDto(santaUsersPairDto);
        SantaUsersPairDto savedPair = santaUserPairRepo.save(santaUsersPairDto);


        SantaUserDto santaUserResponseDto = santaUserRepo.save(santaUserDto);

        long usersComplete = santaUserRepo.countUsersCompletedByAdminId(adminDto.getAdminId());
        long userCreate = santaUserRepo.countUsersCreatedByAdminID(adminDto.getAdminId());

        System.out.println("users com" + usersComplete);
        System.out.println("userCre " + userCreate);

        if (userCreate != 0 && (usersComplete - userCreate) == 0) {
            System.out.println("inside");
            sheduleService.findCompletedUsersAdminAndRunLottery(adminDto.getAdminId());
        }


        return mapToSantaUserFromSantaUserDto(santaUserResponseDto);

    }


    void sendTokenSmsAfterUsersCompleted() {

    }

    public boolean isCorrectAdmin(long id) {
        return adminRepo.existsById(id);
    }

    public AdminDto saveAdmin(AdminDto adminDto) {
        return adminRepo.save(adminDto);
    }

    public CreateUser createUserGenereteTokenAndSendSMS(CreateUser createUser) {
        AdminDto adminDto = adminRepo.getById(createUser.getAdmin().getAdminId());
        SantaUserDto santaUserDto = mapToSantaUserDtoFromCreateUser(createUser);

        santaUserDto.setUserCreate(true);


        santaUserDto.setAdminDto(adminDto);

        SantaUserDto savedSantaUser = santaUserRepo.save(santaUserDto);


        SantaUserDto finalSaveUser = santaUserRepo.save(savedSantaUser);


        return mapToCreateUserFromSantaUserDto(finalSaveUser);
    }


    public SantaUser findUserByTokenId(String token) {

        Optional<SantaUserDto> santaUserDtoByTokenOptional = santaUserRepo.findSantaUserDtoByToken(token);


        if (santaUserDtoByTokenOptional.isPresent()) {
            SantaUser santaUser = mapToSantaUserFromSantaUserDto(santaUserDtoByTokenOptional.get());

            long usersCreated = santaUserRepo.countUsersCreatedByAdminID(santaUser.getAdmin().getAdminId());
            long usersCompleted = santaUserRepo.countUsersCompletedByAdminId(santaUser.getAdmin().getAdminId());
            if (usersCompleted != 0) santaUser.getAdmin().setPercentageCompleteUsers(
                    Integer.parseInt(String.valueOf((usersCompleted * 100) / usersCreated).substring(0, 2)));


            return santaUser;
        } else return new SantaUser(false, 0);
    }


}
