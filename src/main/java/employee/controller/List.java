package employee.controller;

import employee.service.EmployeeServiceImpl;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class List {

    @Autowired
    EmployeeServiceImpl employeeService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @ResponseBody
    @RequestMapping(value = "/companies/{id}/employees", method = RequestMethod.GET, produces = "application/vnd.pl.employee+json")
    public ResponseEntity<java.util.List<? extends  Object>> getAllEmployees(@PathVariable("id") String companyId, HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            java.util.List<Object> employees = employeeService.getAllEmployees(companyId, version);

            if (employees != null) {
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
