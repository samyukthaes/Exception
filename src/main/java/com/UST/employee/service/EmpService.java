package com.UST.employee.service;

import com.UST.employee.entity.Employee;
import com.UST.employee.exception.EmployeeAlreadyPresentException;
import com.UST.employee.exception.IdNotFoundException;
import com.UST.employee.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    @Autowired
    public EmpRepository repo;

    public Employee addNewEmp(Employee emp) {
        Optional<Employee> employee = Optional.ofNullable(repo.findByempId(emp.getEmpId()));
        if(employee.isPresent()){
            throw new EmployeeAlreadyPresentException("This employee with id " + emp.getEmpId() + "is already present");
        }
        else{
            return repo.save(emp);
        }
    }

    public List<Employee> getAllEmpss() {
        return repo.findAll();
    }

    public Employee getEmpsByempId(String empId) {
        Optional<Employee> emp = Optional.ofNullable(repo.findByempId(empId));
        if(emp.isEmpty()){
            throw new IdNotFoundException("Employee with " + empId + " is not found");
        }
        else {
            return repo.findByempId(empId);
        }
    }

    @Transactional
    public String deleteEmpsByempId(String empId) {
        repo.deleteByempId(empId);
        return "Employee Deleted...";
    }

}
