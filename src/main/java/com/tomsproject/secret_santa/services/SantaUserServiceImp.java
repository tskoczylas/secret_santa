package com.tomsproject.secret_santa.services;

import com.tomsproject.secret_santa.entity.Admin;
import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import com.tomsproject.secret_santa.mapper.AdminMapper;
import com.tomsproject.secret_santa.model.AdminDto;
import com.tomsproject.secret_santa.model.TokenUserDto;
import com.tomsproject.secret_santa.repo.AdminRepo;
import com.tomsproject.secret_santa.repo.GameRepo;
import com.tomsproject.secret_santa.repo.SantaUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.tomsproject.secret_santa.mapper.UserMapper.*;


@Service
@RequiredArgsConstructor
public class SantaUserServiceImp implements  UserDetailsService, SantaUserService {

   final   SantaUserRepo santaUserRepo;
   final AdminRepo adminRepo;
   final GameRepo gameRepo;




    @Override
    public TokenUserDto saveUserResponse(TokenUserDto tokenUserDto) {

        try{
            Optional<SantaUserEntity> findUserById = santaUserRepo.findById(tokenUserDto.getUserid());
            if(!tokenUserDto.isValidForSave()) return  new TokenUserDto(false,0);
            else if(findUserById.isPresent() ){
                Optional<GameEntity> findGameDto= gameRepo.findById(findUserById.get().getGameDto().getGameId());

                if(findGameDto.isPresent()){
                SantaUserEntity userDto =mapToSantaUserDtoFromTokenUser(tokenUserDto);
                userDto.setStartMessageSentId(findUserById.get().getStartMessageSentId());
                userDto.setUserComplete(true);
                userDto.setRoleEnum(findUserById.get().getRoleEnum());
                userDto.setGameDto(findGameDto.get());
                userDto.setAdminDto(findGameDto.get().getAdminDto());

                return mapToTokenUserFromSantaUserDto(santaUserRepo.save(userDto));}
            else return new TokenUserDto(false,0);}

            else return new TokenUserDto(false,0);

        }
        catch (Exception e){
            return new TokenUserDto(false,0);
        }
    }


    @Override
    public boolean isCorrectAdmin(long id) {
        return adminRepo.existsById(id);
    }






    @Override
    public TokenUserDto findUserByTokenId(String token) {

        try {
            Optional<SantaUserEntity> optionalTokenUser = santaUserRepo.findById(Long.valueOf(token));
            if (optionalTokenUser.isPresent()) {
                TokenUserDto santaUser = mapToTokenUserFromSantaUserDto(optionalTokenUser.get());
                santaUser.setPercentageCompleteUsers(countActiveUsers(optionalTokenUser.get().getGameDto().getGameId()));
                santaUser.setUserCreate(true);

                return santaUser;
        }
        else return new TokenUserDto(false, 0);
        }
        catch (Exception e) {
            return new TokenUserDto(false, 0);
        }


    }

    @Override
    public Optional<AdminDto> getAdmin(String adminLogin) {


        return adminRepo.findAdminDtoByEmail(adminLogin).map(AdminMapper::mapToAdminFromAdminDto);
    }

    @Override
    public String countActiveUsers(Long gameId) {
        long usersCreated = santaUserRepo.countUsersCreatedByAdminID(gameId);
        long usersCompleted = santaUserRepo.countUsersCompletedByAdminId(gameId);
        if(usersCreated!=0) return String.valueOf((usersCompleted*100)/usersCreated) ;
        else return "0";

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin adminDto = adminRepo.findAdminDtoByEmail(username).orElseThrow(() -> {
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
