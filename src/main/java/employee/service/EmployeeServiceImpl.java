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

    public Object getEmployee(String id, int version) {
        if(version == 1) {
            return getEmployeeJPA(id);
        }
        else if(version >= 2) {
            return getEmployeeMongo(id);
        }
        else {
            return null;
        }

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

        if(id.matches("\\d+")){
            Long empId = Long.valueOf(id);
            Optional<EmployeeMongo> employeeOpt = employeeMongoRepository.findByEmployeeId(empId);

            if(employeeOpt.isPresent()){
                return employeeOpt.get();
            }

            return null;
        }

        return null;
    }

}
