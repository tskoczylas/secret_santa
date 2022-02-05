package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.entity.AdminEntity;
import com.tomsproject.secret_santa.enums.RoleEnum;
import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.tomsproject.secret_santa.mapper.AdminMapper.mapToAdminDtoFromAdmin;

@Service
@Log4j2

public class AdminService {
    @Value("${admin.maxActiveGames}")
    long maxAdminActiveGames;



    final GameRepo gameRepo;
  final PasswordEncoder passwordEncoder;
   final AdminRepo adminRepo;
   final ASESMail asesMail;

    public AdminService( GameRepo gameRepo, PasswordEncoder passwordEncoder, AdminRepo adminRepo, ASESMail asesMail) {

        this.gameRepo = gameRepo;
        this.passwordEncoder = passwordEncoder;
        this.adminRepo = adminRepo;
        this.asesMail = asesMail;
    }

    public boolean isOverActiveGameLimit(long adminId){
        return gameRepo.countGames(adminId,false)>=maxAdminActiveGames;
    }

    public boolean saveAdmin(Admin admin) {
try{
        AdminEntity adminDto = mapToAdminDtoFromAdmin(admin);
        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        adminDto.setRoleEnum(RoleEnum.ADMIN);
        getToken(adminDto);
        AdminEntity savedAdmin = adminRepo.save(adminDto);
    savedAdmin.setEmailConformationSentId(asesMail.sendAccountCreateConfEmail(savedAdmin));
    adminRepo.save(savedAdmin);
    



    return true; }
catch (Exception e){
    log.error("Exception in saveAdmin: " + e.getMessage());
    return false;
}
    }

    private void getToken(AdminEntity adminDto) {
        String token =UUID.randomUUID().toString();
        if(adminRepo.findAdminDtoByActivationToken(token).isEmpty())  adminDto.setActivationToken(token);
        else getToken(adminDto);
    }

    public boolean isAdminEmailExist(Admin admin) {
        try {
            return adminRepo.findAdminDtoByEmail(admin.getEmail()).isPresent() ;

        }
        catch (Exception e){
            log.error("Exception in isAdminEmailExist: " + e.getMessage());
            return true;
        }
    }

    public boolean isValidToken(String token) {
        try{
            Optional<AdminEntity> adminOptional = adminRepo.findAdminDtoByActivationToken(token);
            if(adminOptional.isPresent()){
                adminOptional.get().setActive(true);
                adminRepo.save(adminOptional.get());
                return true;
            }
                return false;}
        catch (Exception e){
            log.error("Inside a isValidToken"  + e.getMessage());
            return false;
        }


    }

}
