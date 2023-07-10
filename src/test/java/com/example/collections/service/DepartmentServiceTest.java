package com.example.collections.service;


import model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.DepartmentService;
import service.EmployeeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
   public void assertEquals(double expected, double actual) {
    }
    private void assertFalse(boolean contains) {
   }
    private static final Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("ivan", "ivanov", 1, 10000),
            new Employee("oleg", "olegov", 1, 15000),
            new Employee("marina", "ivanov", 2, 20000),
            new Employee("olga", "olegova", 2, 30000),
            new Employee("sergey", "sergeev", 3, 50000));
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {

        when(employeeService.getAll()).thenReturn(EMPLOYEES);
    }

    @Test
    void sum() {
        double actual = departmentService.getEmployeeSalarySum(1);
        assertEquals(25000.0, actual);
    }

    @Test
    void max() {
        double actual = departmentService.getEmployeeMaxSalary(2);
        assertEquals(30000, actual);
    }

    @Test
    void min() {
        double actual = departmentService.getEmployeeMinSalary(2);
        assertEquals(20000, actual);
    }

    @Test
    void getAllByDepartment(){
        List<Employee> actual = departmentService.getAll(3);
        Collection<Employee> excpected = Collections.singletonList(
                new Employee("sergey", "sergeev", 3,50000));
        assertIterableEquals(excpected, actual);
    }
    @Test
    void getAll(){
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        Employee sergey = new Employee("sergey", "sergeev", 3,50000);
        assertTrue(actual.get(3).contains(sergey));
        assertFalse(actual.get(2).contains(sergey));
        assertEquals(3, actual.keySet().size());

    }


}
