package com.UST.employee.service;

import com.UST.employee.entity.Employee;
import com.UST.employee.repository.EmpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class EmpServiceTest {

    private EmpService empServiceUnderTest;

    @BeforeEach
    void setUp() {
        empServiceUnderTest = new EmpService();
        empServiceUnderTest.repo = mock(EmpRepository.class);
    }

    @Test
    void testAddNewEmp() {
        // Setup
        final Employee emp = new Employee(0, "empId", "empname", "email", "designation", "hr");
        final Employee expectedResult = new Employee(0, "empId", "empname", "email", "designation", "hr");

        // Configure EmpRepository.save(...).
        final Employee employee = new Employee(0, "empId", "empname", "email", "designation", "hr");
        when(empServiceUnderTest.repo.save(
                new Employee(0, "empId", "empname", "email", "designation", "hr"))).thenReturn(employee);

        // Run the test
        final Employee result = empServiceUnderTest.addNewEmp(emp);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAddNewEmp_EmpRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final Employee emp = new Employee(0, "empId", "empname", "email", "designation", "hr");
        when(empServiceUnderTest.repo.save(
                new Employee(0, "empId", "empname", "email", "designation", "hr")))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> empServiceUnderTest.addNewEmp(emp))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testGetAllEmpss() {
        // Setup
        final List<Employee> expectedResult = List.of(
                new Employee(0, "empId", "empname", "email", "designation", "hr"));

        // Configure EmpRepository.findAll(...).
        final List<Employee> employees = List.of(new Employee(0, "empId", "empname", "email", "designation", "hr"));
        when(empServiceUnderTest.repo.findAll()).thenReturn(employees);

        // Run the test
        final List<Employee> result = empServiceUnderTest.getAllEmpss();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllEmpss_EmpRepositoryReturnsNoItems() {
        // Setup
        when(empServiceUnderTest.repo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Employee> result = empServiceUnderTest.getAllEmpss();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetEmpsByempId() {
        // Setup
        final Employee expectedResult = new Employee(0, "empId", "empname", "email", "designation", "hr");

        // Configure EmpRepository.findByempId(...).
        final Employee employee = new Employee(0, "empId", "empname", "email", "designation", "hr");
        when(empServiceUnderTest.repo.findByempId("empId")).thenReturn(employee);

        // Run the test
        final Employee result = empServiceUnderTest.getEmpsByempId("empId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteEmpsByempId() {
        // Setup
        // Run the test
        final String result = empServiceUnderTest.deleteEmpsByempId("empId");

        // Verify the results
        assertThat(result).isEqualTo("Employee Deleted...");
        verify(empServiceUnderTest.repo).deleteByempId("empId");
    }
}
