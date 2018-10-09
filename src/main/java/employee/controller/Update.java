package employee.controller;

import employee.data.EmployeeJPA;
import employee.service.EmployeeServiceImpl;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("employeeUpdate")
public class Update {

    @Autowired
    EmployeeServiceImpl employeeService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/companies/{cid}/employees/{id}", produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends Object> updateEmployee(@PathVariable("cid") String companyId, @PathVariable("id") String employeeId, @RequestBody EmployeeJPA employee, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object updatedEmployee = employeeService.updateEmployee(companyId, employeeId, employee, version);

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
    @RequestMapping(method = RequestMethod.PATCH, value = "/companies/{cid}/employees/{id}", produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends Object> updatePartialEmployee(@PathVariable("cid") String companyId, @PathVariable("id") String employeeId, @RequestBody EmployeeJPA partialEmployee, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object updatedEmployee = employeeService.updatePartialEmployee(companyId, employeeId, partialEmployee, version);

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
