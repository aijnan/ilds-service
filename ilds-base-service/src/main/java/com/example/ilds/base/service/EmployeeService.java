package com.example.ilds.base.service;

import com.example.ilds.base.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    void update(Employee employee);

    void delete(String id);

    Employee findById(String id);

    List<Employee> findAll();

}
