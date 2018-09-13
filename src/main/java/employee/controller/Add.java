package employee.controller;

import employee.data.EmployeeJPA;
import employee.service.EmployeeServiceImpl;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Add {

    @Autowired
    EmployeeServiceImpl employeeService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/companies/{id}/employees", produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends Object> addEmployee(@PathVariable("id") String companyId, @RequestBody EmployeeJPA employee, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object addedEmployee = employeeService.addEmployee(companyId, employee, version);

            if(addedEmployee != null) {
                return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
