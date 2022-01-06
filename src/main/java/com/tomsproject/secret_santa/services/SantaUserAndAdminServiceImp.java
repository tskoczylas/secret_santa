package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.mapper.AdminMapper;
import com.tomsproject.secret_santa.mapper.UserMapper;
import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.CreateUser;
import com.tomsproject.secret_santa.model.SantaUser;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.SantaUserPairRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tomsproject.secret_santa.mapper.AdminMapper.mapToAdminDtoFromAdmin;
import static com.tomsproject.secret_santa.mapper.AdminMapper.mapToAdminFromAdminDto;
import static com.tomsproject.secret_santa.mapper.UserMapper.*;


@Service

public class SantaUserAndAdminServiceImp implements  UserDetailsService, SantaUserAndAdminService {

   private  SantaUserRepo santaUserRepo;
    private AdminRepo adminRepo;
   private SantaUserPairRepo santaUserPairRepo;
   private SmsService smsService;
   private SheduleService sheduleService;
   private PasswordEncoder passwordEncoder;


    public SantaUserAndAdminServiceImp(SantaUserRepo santaUserRepo, AdminRepo adminRepo, SantaUserPairRepo santaUserPairRepo, SmsService smsService, SheduleService sheduleService, PasswordEncoder passwordEncoder) {
        this.santaUserRepo = santaUserRepo;
        this.adminRepo = adminRepo;
        this.santaUserPairRepo = santaUserPairRepo;
        this.smsService = smsService;
        this.sheduleService = sheduleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @PostConstruct
    public void createHardCodedAdmin() {
        if (!adminRepo.existsById(1L)) {

            AdminDto adminDto = new AdminDto();
            adminDto.setFirstName("Tomasz");
            adminDto.setSecondName("Skoczylas");
            adminDto.setFirstChosenPrice(40);
            adminDto.setSecondChosenPrice(50);
            adminDto.setThirdChosenPrice(60);
            adminDto.setPassword("1234");
            adminDto.setLogin("admin");
            adminRepo.save(adminDto);
        }
    }

    @Override
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


    @Override
    public void sendTokenSmsAfterUsersCompleted() {

    }

    @Override
    public boolean isCorrectAdmin(long id) {
        return adminRepo.existsById(id);
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        AdminDto adminDto = mapToAdminDtoFromAdmin(admin);
        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        AdminDto savedAdmin = adminRepo.save(adminDto);
       return mapToAdminFromAdminDto(savedAdmin);
    }

    @Override
    public List<SantaUser> createUserGenereteTokenAndSendSMS(CreateUser createUser) {
        //
        // AdminDto adminDto = adminRepo.getById(createUser.getAdmin().getAdminId());
        AdminDto adminDto = new AdminDto();
        SantaUserDto santaUserDto = mapToSantaUserDtoFromCreateUser(createUser);

        santaUserDto.setUserCreate(true);


        santaUserDto.setAdminDto(adminDto);

        SantaUserDto savedSantaUser = santaUserRepo.save(santaUserDto);


        SantaUserDto finalSaveUser = santaUserRepo.save(savedSantaUser);


        List<SantaUser> listOfUsers = santaUserRepo.
                findAllByAdminDtoAdminId(adminDto.getAdminId()).
                stream().
                map(UserMapper::mapToSantaUserFromSantaUserDto).
                collect(Collectors.toList());


        return listOfUsers;

    }


    @Override
    public SantaUser findUserByTokenId(String token) {

        Optional<SantaUserDto> santaUserDtoByTokenOptional = santaUserRepo.findSantaUserDtoByToken(token);


        if (santaUserDtoByTokenOptional.isPresent()) {
            SantaUser santaUser = mapToSantaUserFromSantaUserDto(santaUserDtoByTokenOptional.get());

            long usersCreated = santaUserRepo.countUsersCreatedByAdminID(santaUser.getAdmin().getAdminId());
            long usersCompleted = santaUserRepo.countUsersCompletedByAdminId(santaUser.getAdmin().getAdminId());
            if(usersCreated>0&&usersCompleted==0)    santaUser.getAdmin().setPercentageCompleteUsers(0);
            else if((usersCreated>0||usersCompleted>0)&&usersCompleted==usersCreated)
                santaUser.getAdmin().setPercentageCompleteUsers(100);
           else if (usersCreated > 0) santaUser.getAdmin().setPercentageCompleteUsers(
                    Integer.parseInt(String.valueOf((usersCompleted * 100) / usersCreated).substring(0, 2)));



            return santaUser;
        } else return new SantaUser(false, 0);
    }

    @Override
    public Optional<Admin> getAdmin(String adminLogin) {

     return    adminRepo.findAdminDtoByLogin(adminLogin).map(AdminMapper::mapToAdminFromAdminDto);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AdminDto adminDto = adminRepo.findAdminDtoByLogin(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("Admin " + username + "not found");
        });

        Collection<SimpleGrantedAuthority> authorities=
                Collections.
                        singleton(new SimpleGrantedAuthority(adminDto.
                                getRoleEnum().
                                name()));

        return new User(adminDto.getLogin(),adminDto.getPassword(),authorities);
    }
}
