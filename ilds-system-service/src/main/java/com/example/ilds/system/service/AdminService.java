package com.example.ilds.system.service;

import com.example.ilds.system.model.dto.LoginDto;
import com.example.ilds.system.model.entity.Admin;

import java.util.List;

public interface AdminService {

    Admin save(Admin admin) throws Exception;

    Admin findById(String id);

    boolean sendEmail(String email) throws Exception;

    Admin loginByPassword(LoginDto dto) throws Exception;

    Admin loginByEmail(LoginDto dto) throws Exception;

    List<Admin> findAll();

    //生成token
    String createToken(Admin admin, long exp);

    void delete(String id);
}
