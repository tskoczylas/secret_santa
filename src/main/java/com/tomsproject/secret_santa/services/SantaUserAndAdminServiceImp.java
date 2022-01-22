package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.enums.RoleEnum;
import com.tomsproject.secret_santa.mapper.AdminMapper;
import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.TokenUser;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
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
import java.util.Optional;

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
   private GameRepo gameRepo;

    public SantaUserAndAdminServiceImp(SantaUserRepo santaUserRepo, AdminRepo adminRepo, SantaUserPairRepo santaUserPairRepo, SmsService smsService, SheduleService sheduleService, PasswordEncoder passwordEncoder, GameRepo gameRepo) {
        this.santaUserRepo = santaUserRepo;
        this.adminRepo = adminRepo;
        this.santaUserPairRepo = santaUserPairRepo;
        this.smsService = smsService;
        this.sheduleService = sheduleService;
        this.passwordEncoder = passwordEncoder;
        this.gameRepo = gameRepo;
    }

    @Override
    @PostConstruct
    public void createHardCodedAdmin() {
        /*
        if (!adminRepo.existsById(1L)) {

            AdminDto adminDto = new AdminDto();
            adminDto.setFirstName("Tomasz");
            adminDto.setSecondName("Skoczylas");
            adminDto.setRoleEnum(RoleEnum.ADMIN);

            adminDto.setPassword(passwordEncoder.encode("1234"));
            adminDto.setEmail("t.skoczylas1@gmail.com");
            adminRepo.save(adminDto);

         */
        }




    @Override
    public TokenUser saveUserResponse(TokenUser tokenUser) {

        try{
            Optional<SantaUserDto> findUserById = santaUserRepo.findById(tokenUser.getUserid());
            if(!tokenUser.isValidForSave()) return  new TokenUser(false,0);
            else if(findUserById.isPresent()){
                Optional<GameDto> findGameDto= gameRepo.findById(findUserById.get().getGameDto().getGameId());
                SantaUserDto userDto =mapToSantaUserDtoFromTokenUser(tokenUser);
                userDto.setStartMessageSentId(findUserById.get().getStartMessageSentId());
                userDto.setUserComplete(true);
                userDto.setRoleEnum(findUserById.get().getRoleEnum());
                userDto.setGameDto(findGameDto.get());
                userDto.setAdminDto(findGameDto.get().getAdminDto());

                return mapToTokenUserFromSantaUserDto(santaUserRepo.save(userDto));}
            else return new TokenUser(false,0);

        }
        catch (Exception e){
            return new TokenUser(false,0);
        }
    }


    @Override
    public boolean isCorrectAdmin(long id) {
        return adminRepo.existsById(id);
    }






    @Override
    public TokenUser findUserByTokenId(String token) {

        try {
            Optional<SantaUserDto> optionalTokenUser = santaUserRepo.findById(Long.valueOf(token));
            if (optionalTokenUser.isPresent()) {
                TokenUser santaUser = mapToTokenUserFromSantaUserDto(optionalTokenUser.get());
                santaUser.setPercentageCompleteUsers(countActiveUsers(optionalTokenUser.get().getGameDto().getGameId()));
                santaUser.setUserCreate(true);

                return santaUser;
        }
        else return new TokenUser(false, 0);
        }
        catch (Exception e) {
            return new TokenUser(false, 0);
        }


    }

    @Override
    public Optional<Admin> getAdmin(String adminLogin) {


        return adminRepo.findAdminDtoByEmail(adminLogin).map(AdminMapper::mapToAdminFromAdminDto);
    }

    @Override
    public String countActiveUsers(Long gameId) {
        long usersCreated = santaUserRepo.countUsersCreatedByAdminID(gameId);
        long usersCompleted = santaUserRepo.countUsersCompletedByAdminId(gameId);
        if(usersCreated!=0) return String.valueOf((usersCompleted*100)/usersCreated) ;
        else return String.valueOf("0");

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AdminDto adminDto = adminRepo.findAdminDtoByEmail(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("Admin " + username + "not found");
        });

        Collection<SimpleGrantedAuthority> authorities=
                Collections.
                        singleton(new SimpleGrantedAuthority(adminDto.
                                getRoleEnum().
                                name()));



        return new User(adminDto.getEmail(),adminDto.getPassword(),adminDto.isActive(),true,true,true,authorities);
    }



}
