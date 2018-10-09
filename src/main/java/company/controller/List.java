package company.controller;

import company.service.CompanyService;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("companyList")
public class List {

    @Autowired
    CompanyService companyService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/companies", method = RequestMethod.GET, produces = "application/vnd.pl.company+json")
    public ResponseEntity<java.util.List<? extends  Object>> getAllCompanies(HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            java.util.List<Object> companies = companyService.getAllCompanies(version);

            if (companies != null) {
                return new ResponseEntity<>(companies, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
