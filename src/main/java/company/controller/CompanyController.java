package company.controller;

import company.data.CompanyJPA;
import company.repository.CompanyJPARepository;
import company.service.CompanyService;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyJPARepository companyJPARepository;

    @Autowired
    CompanyService companyService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @ResponseBody
    @RequestMapping(value = "/companies", method = RequestMethod.GET, produces = "application/vnd.pl.company+json")
    public ResponseEntity<List<? extends  Object>> getAllCompanies(HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            List<Object> companies = companyService.getAllCompanies(version);

            if (companies != null) {
                return new ResponseEntity<>(companies, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.GET, produces = "application/vnd.pl.company+json")
    public ResponseEntity<? extends Object> getCompany(@PathVariable String id, HttpServletRequest request){
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)){
            Object company = companyService.getCompany(id, version);

            if(company != null){
                return new ResponseEntity<>(company, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "/companies", method = RequestMethod.POST, produces = "application/vnd.pl.company+json", consumes = "application/json")
    public ResponseEntity<? extends Object> addCompany(@RequestBody CompanyJPA company, HttpServletRequest request){
        String acceptHeader = request.getHeader("Accept");
        String vnd = headerUtils.getVendor(acceptHeader);
        String vndType = headerUtils.getVendorType(acceptHeader);
        int version = headerUtils.getVersion(acceptHeader);

        if(headerUtils.isValidHeader(vndType)) {
            Object addedCompany = companyService.addCompany(company, version);

            if(addedCompany != null){
                return new ResponseEntity<>(addedCompany, HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

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
