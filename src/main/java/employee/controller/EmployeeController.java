package employee.controller;

import employee.data.EmployeeJPA;
import employee.repository.EmployeeJPARepository;
import employee.repository.EmployeeMongoRepository;
import employee.service.EmployeeServiceImpl;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeJPARepository employeeRepository;

    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    EmployeeServiceImpl employeeService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/employees", produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends Object> addEmployee(@RequestBody EmployeeJPA employee, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object addedEmployee = employeeService.addEmployee(employee, version);

            if(addedEmployee != null) {
                return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/vnd.pl.employee+json")
    public ResponseEntity<List<? extends  Object>> getAllEmployees(HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            List<Object> employees = employeeService.getAllEmployees(version);

            if (employees != null) {
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends  Object> getEmployee(@PathVariable("id") String id, HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
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

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}", produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends Object> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeJPA employee, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
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

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}", produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends Object> deleteEmployee(@PathVariable("id") String id, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
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

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH, value = "employees/{id}", produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends Object> updatePartialEmployee(@PathVariable("id") String id, @RequestBody EmployeeJPA partialEmployee, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object updatedEmployee = employeeService.updatePartialEmployee(id, partialEmployee, version);

            if (updatedEmployee != null) {
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
