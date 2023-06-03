package service;


import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service

public class EmployeeService {
    private static final int MAX_SIZE = 5;
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);

    public EmployeeService(){
        Employee employee1 = new Employee("ivan", "ivanov");
        Employee employee2 = new Employee("oleg", "olegov");
        Employee employee3 = new Employee("marina", "sergeeva");
        Employee employee4 = new Employee("olga", "ivanova");
        employees.put(createKey(employee1),employee1);
        employees.put(createKey(employee2),employee2);
        employees.put(createKey(employee3),employee3);
        employees.put(createKey(employee4),employee4);
    }



    public Collection<Employee> getAll() {return employees.values();}

    public Employee add(Employee employee) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(createKey(employee), employee);
        return employee;
    }

   public Employee find(String firstName, String lastName){
    Employee employee = employees.get(createKey(firstName,lastName));
    if (employee == null) {
        throw new EmployeeNotFoundException();
    }
    return employee;
   }

 public Employee remove(String firstName, String lastName){
    return employees.remove(createKey(firstName, lastName));
 }
 private static String createKey(Employee employee){
    return createKey(employee.getFirstName(), employee.getLastName());
}
 private static String createKey(String firstName, String lastName) {
    return (firstName + lastName).toLowerCase();}
}


