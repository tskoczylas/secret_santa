package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.Admin;
import com.tomsproject.secret_santa.model.AdminDto;
import org.modelmapper.ModelMapper;

public class AdminMapper {

    private  AdminMapper() {
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static AdminDto mapToAdminFromAdminDto(Admin adminDto) {
        AdminDto admin = new AdminDto();
        modelMapper = new ModelMapper();
        modelMapper.map(adminDto, admin);
        return admin;
    }
    public static Admin mapToAdminFromAdminDto(AdminDto adminDto) {
        Admin admin = new Admin();
        modelMapper = new ModelMapper();
        modelMapper.map(adminDto, admin);
        return admin;
    }




}
