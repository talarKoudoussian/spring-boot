package company.controller;

import company.service.CompanyService;
import employee.utility.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("companyView")
public class View {

    @Autowired
    CompanyService companyService;

    HttpRequestUtil headerUtils = new HttpRequestUtil();

    @CrossOrigin(origins = "*")
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
}
