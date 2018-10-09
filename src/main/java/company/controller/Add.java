package company.controller;

import company.data.CompanyJPA;
import company.service.CompanyService;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("companyAdd")
public class Add {

    @Autowired
    CompanyService companyService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
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
}
