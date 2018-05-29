package employee.controller;

import employee.data.EmployeeJPA;
import employee.repository.EmployeeJPARepository;
import employee.repository.EmployeeMongoRepository;
import employee.service.EmployeeServiceImpl;
import employee.utility.HttpRequestUtil;
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

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/employees", produces = { "application/vnd.pl.employee+json" })
    public ResponseEntity<? extends Object> addEmployee(@RequestBody EmployeeJPA employee, HttpServletRequest request) {
        String contentType = request.getContentType();
        String vnd = headerUtils.getVendor(contentType);
        String vndType = headerUtils.getVendorType(contentType);
        int version = headerUtils.getVersion(contentType);

        if(headerUtils.isValidHeader(vnd, vndType)) {
            Object addedEmployee = employeeService.addEmployee(employee, version);

            if(addedEmployee != null) {
                return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = { "application/vnd.pl.employee+json"})
    public ResponseEntity<? extends  Object> getEmployee(@PathVariable("id") String id, HttpServletRequest request) {

        String contentType = request.getContentType();
        String vnd = headerUtils.getVendor(contentType);
        String vndType = headerUtils.getVendorType(contentType);
        int version = headerUtils.getVersion(contentType);

        if(headerUtils.isValidHeader(vnd, vndType)) {
            Object employee = employeeService.getEmployee(id, version);

            if (employee != null) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}", produces = { "application/vnd.pl.employee+json"})
    public ResponseEntity<? extends Object> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeJPA employee, HttpServletRequest request) {
        String contentType = request.getContentType();
        String vnd = headerUtils.getVendor(contentType);
        String vndType = headerUtils.getVendorType(contentType);
        int version = headerUtils.getVersion(contentType);

        if(headerUtils.isValidHeader(vnd, vndType)) {
            Object updatedEmployee = employeeService.updateEmployee(id, employee, version);

            if (updatedEmployee != null) {
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<? extends Object> deleteEmployee(@PathVariable("id") String id, HttpServletRequest request) {
        String contentType = request.getContentType();
        String vnd = headerUtils.getVendor(contentType);
        String vndType = headerUtils.getVendorType(contentType);
        int version = headerUtils.getVersion(contentType);

        if(headerUtils.isValidHeader(vnd, vndType)) {
            Object deletedEmployee = employeeService.deleteEmployee(id, version);

            if(deletedEmployee != null) {
                return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
