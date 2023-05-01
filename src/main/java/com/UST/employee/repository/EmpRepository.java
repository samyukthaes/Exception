package com.UST.employee.repository;

import com.UST.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpRepository extends JpaRepository<Employee,String> {
    Employee findByempId(String empid);

    void deleteByempId(String empId);
}
