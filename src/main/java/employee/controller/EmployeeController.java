package employee.controller;

import employee.data.EmployeeJPA;
import employee.repository.EmployeeJPARepository;
import employee.repository.EmployeeMongoRepository;
import employee.service.EmployeeServiceImpl;
import employee.utility.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeJPARepository employeeRepository;

    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    EmployeeServiceImpl employeeService;

    EmployeeUtil employeeUtil = new EmployeeUtil();

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/employees", produces = "application/json;charset=UTF-8")
    public ResponseEntity<EmployeeJPA> addEmployee(@RequestBody EmployeeJPA employee) {
        System.out.println(employee.toString());

        if(employee.isFirstNameEmpty() || employee.isLastNameEmpty() || employee.isAddedDateEmpty() || employee.isEmploymentStatusEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        EmployeeJPA newEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = { "application/vnd.pl.employee+json"})
    public ResponseEntity<? extends  Object> getEmployee(@PathVariable("id") String id, HttpServletRequest request) {

        String contentType = request.getContentType();
        System.out.println(contentType);
        int version = employeeUtil.checkHeader(contentType);

        if(version == -1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Object employee = employeeService.getEmployee(id, version);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<EmployeeJPA> updateEmployee(@PathVariable("id") String employeeId, @RequestBody EmployeeJPA employee) {
        if(employeeId.matches("\\d+")) {
            Long id = Long.valueOf(employeeId);
            Optional<EmployeeJPA> selectedEmployee = employeeRepository.findById(id);

            if(selectedEmployee.isPresent()) {
                if(employee.isFirstNameEmpty() || employee.isLastNameEmpty() || employee.isAddedDateEmpty() || employee.isEmploymentStatusEmpty()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                EmployeeJPA emp = selectedEmployee.get();
                emp.setFirstName(employee.getFirstName());
                emp.setLastName(employee.getLastName());
                emp.setAddedDate(employee.getAddedDate());
                emp.setEmploymentStatus(employee.getEmploymentStatus());
                EmployeeJPA updatedEmployee = employeeRepository.save(emp);
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
    public ResponseEntity<EmployeeJPA> updatePartialEmployee(@PathVariable("id") String employeeId, @RequestBody EmployeeJPA partialEmployee) {
        if (employeeId.matches("\\d+")) {
            Long id = Long.valueOf(employeeId);
            Optional<EmployeeJPA> selectedEmployee = employeeRepository.findById(id);

            if(selectedEmployee.isPresent()) {
                EmployeeJPA emp = selectedEmployee.get();

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

                EmployeeJPA updatedEmployee = employeeRepository.save(emp);
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
