package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/employee")
    public Long addEmployee(@RequestBody Employee employee){

        employeeRepository.save(employee);

        return employee.getEmployeeId();
    }

}
