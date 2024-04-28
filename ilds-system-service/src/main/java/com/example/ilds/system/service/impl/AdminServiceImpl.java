package com.example.ilds.system.service.impl;

import com.example.common.util.DataTimeUtil;
import com.example.ilds.system.model.dto.LoginDto;
import com.example.ilds.system.model.entity.Admin;
import com.example.ilds.system.repository.AdminRepository;
import com.example.ilds.system.service.AdminService;
import com.example.ilds.system.service.EmailService;
import com.example.ilds.system.util.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminRepository adminRepository;

    @Resource
    private EmailService emailService;

    @Override
    public Admin save(Admin admin) throws Exception {
        if (admin.getEmail().length() < 8 || admin.getPassword().length() < 5) throw new Exception("请求参数异常");
        admin.setCreateAt(DataTimeUtil.getNowTimeString());
        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(String id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public boolean sendEmail(String email) throws Exception {
        Admin admin = adminRepository.findAdminByEmail(email);
        if (admin == null) throw new Exception("不存在的邮箱账户");
        return emailService.sendVerificationCode(email);
    }

    @Override
    public Admin loginByPassword(LoginDto dto) throws Exception {
        Admin one = adminRepository.findAdminByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (one == null) {
            throw new Exception("邮箱或密码错误");
        }
        return one;
    }

    @Override
    public Admin loginByEmail(LoginDto dto) throws Exception {
        boolean status = emailService.checkVerificationCode(dto.getEmail(), dto.getCode());
        if (!status) throw new Exception("验证码错误");
        return adminRepository.findAdminByEmail(dto.getEmail());
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public String createToken(Admin admin, long exp) {
        String rolesString = admin.getRoles();
        String[] roles = rolesString != null ? rolesString.split(";") : null;
        return JwtTokenUtil.createToken(admin.getEmail(), roles, exp);
    }

    @Override
    public void delete(String id) {
        adminRepository.deleteById(id);
    }

}
