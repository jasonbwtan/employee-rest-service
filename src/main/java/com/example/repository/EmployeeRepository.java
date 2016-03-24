package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
