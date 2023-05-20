package service;


import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int MAX_SIZE = 5;

    public Employee add(String firstName, String lastName) {
        if (employees.size()>= MAX_SIZE){
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.contains(employeeToAdd)){
            throw new EmployeeAlreadyAddedException();
        }

        employees.add(employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName){
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.contains(employeeToRemove)){
            throw new EmployeeNotFoundException();
        }
        employees.remove(employeeToRemove);
        return employeeToRemove;
    }

    public Employee find(String firstName, String lastName){
        for (Employee employee : employees) {

            if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
    public List<Employee> getAll(){
        return Collections.unmodifiableList(employees);
    }
}




