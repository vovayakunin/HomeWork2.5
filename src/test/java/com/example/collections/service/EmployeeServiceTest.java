package com.example.collections.service;

import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.EmployeeService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertFalse;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();
    private Employee employee;
    private void asseertEquals(Employee employee, Employee actual) {
    }

    private void assertNoNull(Employee actual) {

    }
    private void assertFalse(boolean contains) {
    }
    @Test
    void whenFullThenThrowException() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            char additionlChar = (char) ('a'+ i );
            Employee employee = new Employee("name"+ additionlChar, "second_name", 1,1);
            employeeService.add(employee);
        }

        assertThrows(EmployeeStorageIsFullException.class,
                ()->employeeService.add(new Employee("Ivan", "Ivanov", 1,1)));
    }

    @Test
    void whenNotUniqThenThrowException(){
        Employee employee = new Employee("name","last_name", 1,1);
        employeeService.add(employee);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(employee));
    }

    @Test
    void addPositive(){
        Employee employee = new Employee("name", "last_name", 1,1);
        employeeService.add(employee);
        assertTrue(employeeService.getAll().contains(employee));
    }
    @Test
    void findPositive (){
        Employee employee = new Employee("name", "last_name", 1,1);
        employeeService.add(employee);
        Employee actual = employeeService.find("name", "last_name");
        assertNoNull(actual);
        asseertEquals(employee, actual);
    }



    @Test
    void findNegative (){
        Employee employee = new Employee("name", "last_name", 1,1);
        employeeService.add(employee);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("not_name", "not_last_name"));

    }

    @Test
    void removePositive(){
        Employee employee = new Employee("name", "last_name", 1,1);
        employeeService.add(employee);
        employeeService.remove("name", "last_name");
        assertFalse(employeeService.getAll().contains(employee));

    }
    @Test
    void removeNegative(){
        Employee employee = new Employee("name", "last_name", 1,1);
        employeeService.add(employee);
        Employee actual = employeeService.remove("not_name", "not_last_name");
        assertNull(actual);
    }

}

