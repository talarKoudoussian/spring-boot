package company.controller;

import company.data.CompanyJPA;
import company.service.CompanyService;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("companyUpdate")
public class Update {

    @Autowired
    CompanyService companyService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.PUT, produces = "application/vnd.pl.company+json", consumes = "application/json")
    public ResponseEntity<? extends Object> updateCompany(@PathVariable String id, @RequestBody CompanyJPA company, HttpServletRequest request){
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object updatedCompany = companyService.updateCompany(id, company, version);

            if(updatedCompany != null) {
                return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "companies/{id}", method = RequestMethod.PATCH, produces = "application/vnd.pl.company+json", consumes = "application/json")
    public ResponseEntity<? extends Object> updatePartialCompany(@PathVariable String id, @RequestBody CompanyJPA company, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object updatedCompany = companyService.updatePartialCompany(id, company, version);

            if(updatedCompany != null) {
                return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
