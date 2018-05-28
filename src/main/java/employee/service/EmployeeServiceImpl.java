package employee.service;

import employee.data.EmployeeJPA;
import employee.data.EmployeeMongo;
import employee.repository.EmployeeJPARepository;
import employee.repository.EmployeeMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeJPARepository employeeJPARepository;

    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    @Override
    public Object getEmployee(String id, double version) {
        Object returnEmployee;
        String strVersion = String.valueOf(version);

        switch (strVersion) {
            case "-1" : returnEmployee = null;
                        break;
            case "1.0" : returnEmployee = getEmployeeJPA(id);
                        break;
            case "2.0" : returnEmployee = getEmployeeMongo(id);
                        break;
            default: returnEmployee = getEmployeeMongo(id);
                        break;
        }

        return returnEmployee;
    }

    public EmployeeJPA getEmployeeJPA(String id) {

        if(id.matches("\\d+")) {
            Long empId = Long.valueOf(id);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.findById(empId);

            if(employeeOpt.isPresent()) {
                return employeeOpt.get();
            }

            return null;
        }

        return null;
    }

    public EmployeeMongo getEmployeeMongo(String id) {

        if(id.matches("\\d+")) {
            Long empId = Long.valueOf(id);
            Optional<EmployeeMongo> employeeOpt = employeeMongoRepository.findByEmployeeId(empId);

            if(employeeOpt.isPresent()) {
                return employeeOpt.get();
            }

            return null;
        }

        return null;
    }

}
