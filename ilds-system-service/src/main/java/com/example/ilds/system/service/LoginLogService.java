package com.example.wms.system.service;

import com.example.wms.system.model.dto.LoginDto;
import com.example.wms.system.model.entity.Admin;
import com.example.wms.system.model.entity.LoginLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginLogService {
    List<LoginLog> getAll();
    void recordLog(LoginDto loginDto, Admin admin, HttpServletRequest request);
    void delLoginLog(String id);
}
