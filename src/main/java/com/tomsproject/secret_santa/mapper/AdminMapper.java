package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.model.Admin;
import com.tomsproject.secret_santa.model.SantaUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public class AdminMapper {

    static ModelMapper modelMapper = new ModelMapper();

    public static Admin mapToAdminFromAdminDto(AdminDto adminDto) {
        Admin admin = new Admin();
        modelMapper = new ModelMapper();
        modelMapper.map(adminDto, admin);
        return admin;
    }
    public static AdminDto mapToAdminDtoFromAdmin(Admin admin) {
        AdminDto adminDto = new AdminDto();
        modelMapper = new ModelMapper();
        modelMapper.map(admin, adminDto);
        return adminDto;
    }




}
