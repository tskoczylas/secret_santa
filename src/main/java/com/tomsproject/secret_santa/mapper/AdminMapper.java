package com.tomsproject.secret_santa.mapper;

import com.tomsproject.secret_santa.entity.AdminEntity;
import com.tomsproject.secret_santa.model.Admin;
import org.modelmapper.ModelMapper;

public class AdminMapper {

    private  AdminMapper() {
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static Admin mapToAdminFromAdminDto(AdminEntity adminDto) {
        Admin admin = new Admin();
        modelMapper = new ModelMapper();
        modelMapper.map(adminDto, admin);
        return admin;
    }
    public static AdminEntity mapToAdminDtoFromAdmin(Admin admin) {
        AdminEntity adminEntity = new AdminEntity();
        modelMapper = new ModelMapper();
        modelMapper.map(admin, adminEntity);
        return adminEntity;
    }




}
