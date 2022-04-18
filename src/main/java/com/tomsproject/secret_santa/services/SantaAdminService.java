package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.entity.Admin;
import com.tomsproject.secret_santa.enums.RoleEnum;
import com.tomsproject.secret_santa.exception.SecretServiceException;
import com.tomsproject.secret_santa.model.AdminDto;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.tomsproject.secret_santa.mapper.AdminMapper.mapToAdminFromAdminDto;

@Service
@Log4j2
@RequiredArgsConstructor
public class SantaAdminService {


    @Value("${admin.maxActiveGames}")
    long maxAdminActiveGames;
    final GameRepo gameRepo;
    final PasswordEncoder passwordEncoder;
    final AdminRepo adminRepo;
    final ASESMail asesMail;

    public boolean isOverActiveGameLimit(long adminId) {
        return gameRepo.countGames(adminId, false) >= maxAdminActiveGames;
    }

    public Admin saveAdmin(AdminDto admin) {
        try {
            //map to entity, encode password, set role
            Admin savedPassRoleAdmin = savePassRoleAdmin(admin);

            generateUniqueToken(savedPassRoleAdmin);

            Admin createdAdmin = adminRepo.save(savedPassRoleAdmin);

            String confEmailId = asesMail.sendAccountCreateConfEmail(createdAdmin);

            createdAdmin.setEmailConformationSentId(confEmailId);

            return adminRepo.save(createdAdmin);

        } catch (Exception e) {
            log.error("Cannot save, email: " + admin.getEmail(), e);
            throw new SecretServiceException("Cannot save, email: " + admin.getEmail());
        }
    }

    private Admin savePassRoleAdmin(AdminDto adminDto) {
        Admin admin = mapToAdminFromAdminDto(adminDto);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoleEnum(RoleEnum.ADMIN);
        return admin;
    }

    private void generateUniqueToken(Admin adminDto) {
        String token = UUID.randomUUID().toString();
        if (adminRepo.findAdminDtoByActivationToken(token).isEmpty()) {
            adminDto.setActivationToken(token);
        } else {
            generateUniqueToken(adminDto);
        }
    }

    public boolean isAdminEmailExist(AdminDto admin) {
        try {
            return adminRepo.findAdminDtoByEmail(admin.getEmail()).isPresent();

        } catch (Exception e) {
            throw new SecretServiceException("isAdminEmailExist: " + e.getMessage());
        }
    }

    public boolean isValidToken(String token) {
        try {
            Optional<Admin> adminOptional = adminRepo.findAdminDtoByActivationToken(token);
            if (adminOptional.isPresent()) {
                adminOptional.get().setActive(true);
                adminRepo.save(adminOptional.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Inside a isValidToken" + e.getMessage());
            return false;
        }
    }
}
