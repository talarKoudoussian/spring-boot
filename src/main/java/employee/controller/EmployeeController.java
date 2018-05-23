package employee.controller;

import employee.data.Employee;
import employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/employees", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        System.out.println(employee.toString());

        if(employee.isFirstNameEmpty() || employee.isLastNameEmpty() || employee.isAddedDateEmpty() || employee.isEmploymentStatusEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee newEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String employeeId) {
        if(employeeId.matches("\\d+")) {
            Long id = Long.valueOf(employeeId);
            Optional<Employee> employee = employeeRepository.findById(id);

            if(employee.isPresent()) {
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String employeeId, @RequestBody Employee employee) {
        if(employeeId.matches("\\d+")) {
            Long id = Long.valueOf(employeeId);
            Optional<Employee> selectedEmployee = employeeRepository.findById(id);

            if(selectedEmployee.isPresent()) {
                if(employee.isFirstNameEmpty() || employee.isLastNameEmpty() || employee.isAddedDateEmpty() || employee.isEmploymentStatusEmpty()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                Employee emp = selectedEmployee.get();
                emp.setFirstName(employee.getFirstName());
                emp.setLastName(employee.getLastName());
                emp.setAddedDate(employee.getAddedDate());
                emp.setEmploymentStatus(employee.getEmploymentStatus());
                Employee updatedEmployee = employeeRepository.save(emp);
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String employeeId) {
        if(employeeId.matches("\\d+")){
            Long id = Long.valueOf(employeeId);

            if(employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH, value = "employees/{id}", produces = "application/json;charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updatePartialEmployee(@PathVariable("id") String employeeId, @RequestBody Employee partialEmployee) {
        if (employeeId.matches("\\d+")) {
            Long id = Long.valueOf(employeeId);
            Optional<Employee> selectedEmployee = employeeRepository.findById(id);

            if(selectedEmployee.isPresent()) {
                Employee emp = selectedEmployee.get();

                if(!partialEmployee.isFirstNameEmpty()) {
                    emp.setFirstName(partialEmployee.getFirstName());
                }

                if(!partialEmployee.isLastNameEmpty()) {
                    emp.setLastName(partialEmployee.getLastName());
                }

                if(!partialEmployee.isAddedDateEmpty()) {
                    emp.setAddedDate(partialEmployee.getAddedDate());
                }

                if(!partialEmployee.isEmploymentStatusEmpty()) {
                    emp.setEmploymentStatus(partialEmployee.getEmploymentStatus());
                }

                Employee updatedEmployee = employeeRepository.save(emp);
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
