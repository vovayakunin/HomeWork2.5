package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public double getEmployeeWithMaxSalary(@RequestParam("departmentId")int department) {
        return departmentService.getEmployeeMaxSalary(department);
    }
    @GetMapping("/min-salary")
    public double getEmployeeWithMinSalary(@RequestParam("departmentId")int department) {
        return departmentService.getEmployeeMinSalary(department);
    }
    @GetMapping("/sum")
    public double getEmployeeSalarySum(@RequestParam("departmentId")int department) {
        return departmentService.getEmployeeSalarySum(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getAll(@RequestParam("departmentId") int department){
        return departmentService.getAll(department);
    }
    @GetMapping("/all")
    private Map<Integer,List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
