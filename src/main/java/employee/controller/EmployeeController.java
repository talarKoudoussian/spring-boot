package employee.controller;

import employee.data.Employee;
import employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/employees", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        System.out.println(employee.toString());
        Employee newEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
}
