package com.luv2code.springboot.crudemployee.dao;

import com.luv2code.springboot.crudemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
