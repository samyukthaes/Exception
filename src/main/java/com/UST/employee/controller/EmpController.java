package com.UST.employee.controller;

import com.UST.employee.entity.Employee;
import com.UST.employee.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    public EmpService empservice;

    @PostMapping("/add")
    public Employee addEmp(@RequestBody Employee emp){
        return empservice.addNewEmp(emp);
    }

    @GetMapping("/emps")
    public List<Employee> getAllEmps(){
        return empservice.getAllEmpss();
    }

    @GetMapping("/emps/{empId}")
    public Employee getEmpById(@PathVariable String empId){
        return empservice.getEmpsByempId(empId);
    }

    @DeleteMapping("/emps/{empId}")
    public String deleteEmpById(@PathVariable String empId){
        return empservice.deleteEmpsByempId(empId);
    }

}
