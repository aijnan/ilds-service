package com.example.ilds.system.controller;

import com.example.ilds.system.model.enums.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @GetMapping("")
    public Role[] list() {
        return Role.ROLES;
    }

}
