package com.UST.employee.exception;

public class EmployeeAlreadyPresentException extends RuntimeException{

    public EmployeeAlreadyPresentException(String s) {
        super(s);
    }
}
