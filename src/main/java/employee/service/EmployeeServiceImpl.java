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
        int versionIntNumber = (int) Math.floor(version);

        switch (versionIntNumber) {
            case 1 : {
                returnEmployee = getEmployeeJPA(id);
                break;
            }
            case 2 : {
                returnEmployee = getEmployeeMongo(id);
                break;
            }
            default: {
                returnEmployee = getEmployeeMongo(id);
                break;
            }
        }

        return returnEmployee;
    }

    private EmployeeJPA getEmployeeJPA(String id) {

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

    private EmployeeMongo getEmployeeMongo(String id) {

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
