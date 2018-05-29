package employee.service;

import employee.data.EmployeeJPA;
import employee.data.EmployeeMongo;
import employee.repository.EmployeeJPARepository;
import employee.repository.EmployeeMongoRepository;
import javassist.bytecode.stackmap.TypeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeJPARepository employeeJPARepository;

    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    @Override
    public Object getEmployee(String id, double version) {
        Object returnEmployee;
        int versionIntNumber = (int) Math.floor(version);
        LOGGER.log(Level.INFO, "version to call: " + versionIntNumber + " or DEFAULT");

        switch (versionIntNumber) {
            case 1 : returnEmployee = getEmployeeJPA(id);
                        break;
            case 2 : returnEmployee = getEmployeeMongo(id);
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
