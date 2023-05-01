package com.UST.employee.controller;

import com.UST.employee.entity.Employee;
import com.UST.employee.service.EmpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmpController.class)
class EmpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpService mockEmpservice;

    @Test
    void testAddEmp() throws Exception {
        // Setup
        // Configure EmpService.addNewEmp(...).
        final Employee employee = new Employee(0, "empId", "empname", "email", "designation", "hr");
        when(mockEmpservice.addNewEmp(new Employee(0, "empId", "empname", "email", "designation", "hr")))
                .thenReturn(employee);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/emp/add")
                        .content(asJsonString(employee)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    private String asJsonString(Employee employee) {
        try{
            return new ObjectMapper().writeValueAsString(employee);
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }


    @Test
    void testGetAllEmps() throws Exception {
        // Setup
        // Configure EmpService.getAllEmpss(...).
        final List<Employee> employees = List.of(new Employee(0, "empId", "empname", "email", "designation", "hr"));
        when(mockEmpservice.getAllEmpss()).thenReturn(employees);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/emp/emps")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllEmps_EmpServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEmpservice.getAllEmpss()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/emp/emps")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       // assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetEmpById() throws Exception {
        // Setup
        // Configure EmpService.getEmpsByempId(...).
        final Employee employee = new Employee(0, "empId", "empname", "email", "designation", "hr");
        when(mockEmpservice.getEmpsByempId("empId")).thenReturn(employee);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/emp/emps/{empId}", "empId")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDeleteEmpById() throws Exception {
        // Setup
        when(mockEmpservice.deleteEmpsByempId("empId")).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/emp/emps/{empId}", "empId")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       // assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
