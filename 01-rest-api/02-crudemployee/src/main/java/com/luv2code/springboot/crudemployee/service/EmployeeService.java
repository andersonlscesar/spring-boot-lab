package com.luv2code.springboot.crudemployee.service;

import com.luv2code.springboot.crudemployee.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}
