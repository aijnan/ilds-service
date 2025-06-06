package com.example.ilds.system.repository;

import com.example.ilds.system.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findAdminByEmailAndPassword(String email, String password);

    Admin findAdminByEmail(String email);

    boolean existsAdminByRoles(String roles);

}
