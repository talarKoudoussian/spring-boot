package employee.controller;

import employee.service.EmployeeServiceImpl;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class View {

    @Autowired
    EmployeeServiceImpl employeeService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/companies/{cid}/employees/{id}", method = RequestMethod.GET, produces = "application/vnd.pl.employee+json")
    public ResponseEntity<? extends  Object> getEmployee(@PathVariable("cid") String companyId, @PathVariable("id") String employeeId, HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object employee = employeeService.getEmployee(companyId, employeeId, version);

            if (employee != null) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
