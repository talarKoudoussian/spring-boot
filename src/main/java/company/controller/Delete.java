package company.controller;

import company.service.CompanyService;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("companyDelete")
public class Delete {

    @Autowired
    CompanyService companyService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.DELETE, produces = "application/vnd.pl.company+json")
    public ResponseEntity<? extends Object> deleteCompany(@PathVariable String id, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            boolean isCompanyDeleted = companyService.deleteCompany(id, version);

            if(isCompanyDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
