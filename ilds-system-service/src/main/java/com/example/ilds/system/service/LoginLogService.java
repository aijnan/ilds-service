package com.example.ilds.system.service;

import com.example.ilds.system.model.dto.LoginDto;
import com.example.ilds.system.model.entity.LoginLog;
import com.example.ilds.system.model.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginLogService {
    List<LoginLog> getAll();
    void recordLog(LoginDto loginDto, Admin admin, HttpServletRequest request);
    void delLoginLog(String id);
}
