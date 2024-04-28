package com.example.ilds.system.service.impl;

import com.example.ilds.system.model.dto.LoginDto;
import com.example.ilds.system.model.entity.Admin;
import com.example.ilds.system.model.entity.LoginLog;
import com.example.ilds.system.repository.LoginLogRepository;
import com.example.ilds.system.service.LoginLogService;
import com.example.ilds.system.util.BrowserUtil;
import com.example.ilds.system.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    private LoginLogRepository loginLogRepository;

    @Override
    public List<LoginLog> getAll() {
        return loginLogRepository.findAll();
    }

    @Override
    public void recordLog(LoginDto loginDto, Admin admin, HttpServletRequest request) {
        //创建日志对象
        LoginLog loginLog = new LoginLog();
        loginLog.setDate(new Date());
        loginLog.setEmail(loginDto.getEmail());
        //获取浏览器版本
        loginLog.setBrowser(BrowserUtil.getBrower(request));
        loginLog.setIp(IpUtil.getIpAddr(request));
        if (admin == null){
            loginLog.setStatus(0);
        }else {
            loginLog.setStatus(1);
        }
        //将日志记录写入数据库
        loginLogRepository.save(loginLog);
    }

    @Override
    public void delLoginLog(String id) {
        loginLogRepository.deleteById(id);
    }
}
