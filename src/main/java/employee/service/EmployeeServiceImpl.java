package employee.service;

import employee.data.EmployeeJPA;
import employee.data.EmployeeMongo;
import employee.repository.EmployeeJPARepository;
import employee.repository.EmployeeMongoRepository;
import employee.utility.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeJPARepository employeeJPARepository;

    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    @Override
    public Object getEmployee(String id, int version) {
        Object returnEmployee = null;

        switch (version) {
            case 1: {
                returnEmployee = getEmployeeJPA(id);
                break;
            }
            case 2:
            default: {
                returnEmployee = getEmployeeMongo(id);
                break;
            }
        }

        return returnEmployee;
    }

    @Override
    public List<Object> getAllEmployees(int version) {
        List<Object> employeesList = new ArrayList<>();

        switch (version) {
            case 1: {
                List<EmployeeJPA> employeesJPA = getAllEmployeesJPA();
                for (EmployeeJPA employee: employeesJPA) {
                    employeesList.add(employee);
                }
                break;
            }
            case 2:
            default: {
                List<EmployeeMongo> employeesMongo = getAllEmployeesMongo();
                for (EmployeeMongo employee: employeesMongo) {
                    employeesList.add(employee);
                }
                break;
            }
        }

        return employeesList;
    }

    @Override
    public Object addEmployee(EmployeeJPA employeeJPA, int version) {
        Object addedEmployee = null;

        switch (version) {
            case 1: {
                addedEmployee = addEmployeeJPA(employeeJPA);
                break;
            }

            case 2:
            default: {
                EmployeeMongo employeeMongo = toEmployeeMongo(employeeJPA);
                addedEmployee = addEmployeeMongo(employeeMongo);
                break;
            }
        }

        return addedEmployee;
    }

    @Override
    public Object updateEmployee(String id, EmployeeJPA employee, int version) {
        Object updatedEmployee = null;

        switch (version) {
            case 1: {
                updatedEmployee = updateEmployeeJPA(id, employee);
                break;
            }
            case 2:
            default: {
                updatedEmployee = updateEmployeeMongo(id, employee);
                break;
            }
        }

        return updatedEmployee;
    }

    @Override
    public Object deleteEmployee(String id, int version) {
        Object deletedEmployee = null;

        switch (version) {
            case 1: {
                deletedEmployee = deleteEmployeeJPA(id);
                break;
            }
            case 2:
            default: {
                deletedEmployee = deleteEmployeeMongo(id);
                break;
            }
        }

        return deletedEmployee;
    }

    @Override
    public Object updatePartialEmployee(String id, EmployeeJPA partialEmployeeJPA, int version) {
        Object updatedEmployee = null;

        switch (version) {
            case 1: {
                updatedEmployee = updatePartialEmployeeJPA(id, partialEmployeeJPA);
                break;
            }
            case 2:
            default: {
                EmployeeMongo partialEmployeeMongo = toEmployeeMongo(partialEmployeeJPA);
                updatedEmployee = updatePartialEmployeeMongo(id, partialEmployeeMongo);
                break;
            }
        }

        return updatedEmployee;
    }

    private List<EmployeeJPA> getAllEmployeesJPA() {
        List<EmployeeJPA> employees =  new ArrayList<>();

        Iterable<EmployeeJPA> allEmployees = employeeJPARepository.findAll();
        allEmployees.forEach(employees::add);

        return employees;
    }

    private List<EmployeeMongo> getAllEmployeesMongo() {
        List<EmployeeMongo> employees = employeeMongoRepository.findAll();
        return employees;
    }

    private EmployeeJPA updatePartialEmployeeJPA(String id, EmployeeJPA partialEmployeeJPA) {
        EmployeeJPA updatedEmployee = null;

        if (id.matches("\\d+")) {
            Long empId = Long.valueOf(id);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.findById(empId);

            if (employeeOpt.isPresent()) {
                EmployeeJPA selectedEmployee = employeeOpt.get();

                if(!partialEmployeeJPA.isFirstNameEmpty()) {
                    selectedEmployee.setFirstName(partialEmployeeJPA.getFirstName());
                }

                if(!partialEmployeeJPA.isLastNameEmpty()) {
                    selectedEmployee.setLastName(partialEmployeeJPA.getLastName());
                }

                if(!partialEmployeeJPA.isEmploymentStatusEmpty()) {
                    selectedEmployee.setEmploymentStatus(partialEmployeeJPA.getEmploymentStatus());
                }

                updatedEmployee = employeeJPARepository.save(selectedEmployee);
            }
        }

        return updatedEmployee;
    }

    private EmployeeMongo updatePartialEmployeeMongo(String id, EmployeeMongo partialEmployeeMongo) {
        EmployeeMongo updatedEmployee = null;

        if (id.matches("^[a-zA-Z0-9_.-]*$")) {
            Optional<EmployeeMongo> employeeOpt = employeeMongoRepository.findBy_id(id);

            if (employeeOpt.isPresent()) {
                EmployeeMongo selectedEmployee = employeeOpt.get();

                if(!partialEmployeeMongo.isFirstNameEmpty()) {
                    selectedEmployee.setFirstName(partialEmployeeMongo.getFirstName());
                }

                if(!partialEmployeeMongo.isLastNameEmpty()) {
                    selectedEmployee.setLastName(partialEmployeeMongo.getLastName());
                }

                if(!partialEmployeeMongo.isEmploymentStatusEmpty()) {
                    selectedEmployee.setEmploymentStatus(partialEmployeeMongo.getEmploymentStatus());
                }

                updatedEmployee = employeeMongoRepository.save(selectedEmployee);
            }
        }

        return updatedEmployee;
    }

    private EmployeeJPA getEmployeeJPA(String id) {
        EmployeeJPA selectedEmployee = null;

        if (id.matches("\\d+")) {
            Long empId = Long.valueOf(id);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.findById(empId);

            if (employeeOpt.isPresent()) {
                selectedEmployee = employeeOpt.get();
            }
        }

        return selectedEmployee;
    }

    private EmployeeMongo getEmployeeMongo(String id) {
        EmployeeMongo selectedEmployee = null;

        if (id.matches("^[a-zA-Z0-9_.-]*$")) {
            Optional<EmployeeMongo> employeeOpt = employeeMongoRepository.findBy_id(id);

            if (employeeOpt.isPresent()) {
                selectedEmployee = employeeOpt.get();
            }
        }

        return selectedEmployee;
    }

    private EmployeeMongo deleteEmployeeMongo(String id) {
        EmployeeMongo deletedEmployee = null;

        if(id.matches("^[a-zA-Z0-9_.-]*$")){
            Optional<EmployeeMongo> employeeOpt = employeeMongoRepository.findBy_id(id);

            if(employeeOpt.isPresent()) {
                employeeMongoRepository.deleteBy_id(id);
                deletedEmployee = employeeOpt.get();
            }
        }

        return deletedEmployee;
    }

    private EmployeeJPA deleteEmployeeJPA(String id) {
        EmployeeJPA deletedEmployee = null;

        if(id.matches("\\d+")){
            Long empId = Long.valueOf(id);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.findById(empId);

            if(employeeOpt.isPresent()) {
                employeeJPARepository.deleteById(empId);
                deletedEmployee = employeeOpt.get();
            }
        }

        return deletedEmployee;
    }

    private EmployeeJPA updateEmployeeJPA(String id, EmployeeJPA employeeJPA) {
        EmployeeJPA updatedEmployee = null;

        if (id.matches("\\d+")) {
            Long empId = Long.valueOf(id);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.findById(empId);

            if (employeeOpt.isPresent()) {

                if(!(employeeJPA.isFirstNameEmpty() || employeeJPA.isLastNameEmpty() || employeeJPA.isEmploymentStatusEmpty())) {
                    EmployeeJPA emp = employeeOpt.get();
                    emp.setFirstName(employeeJPA.getFirstName());
                    emp.setLastName(employeeJPA.getLastName());
                    emp.setEmploymentStatus(employeeJPA.getEmploymentStatus());
                    updatedEmployee = employeeJPARepository.save(emp);
                }
            }
        }

        return updatedEmployee;
    }

    private EmployeeMongo updateEmployeeMongo(String id, EmployeeJPA employeeJPA) {
        EmployeeMongo employeeMongo = toEmployeeMongo(employeeJPA);
        EmployeeMongo updatedEmployee = null;

        if (id.matches("^[a-zA-Z0-9_.-]*$")) {
            Optional<EmployeeMongo> employeeOpt = employeeMongoRepository.findBy_id(id);

            if (employeeOpt.isPresent()) {

                if(!(employeeMongo.isFirstNameEmpty() || employeeMongo.isLastNameEmpty() || employeeMongo.isEmploymentStatusEmpty())) {
                    EmployeeMongo emp = employeeOpt.get();
                    emp.setFirstName(employeeMongo.getFirstName());
                    emp.setLastName(employeeMongo.getLastName());
                    emp.setEmploymentStatus(employeeMongo.getEmploymentStatus());
                    updatedEmployee = employeeMongoRepository.save(emp);
                }
            }
        }

        return updatedEmployee;
    }

    private EmployeeJPA addEmployeeJPA(EmployeeJPA employeeJPA) {
        String dateNow = new Date().dateNowToIso();
        employeeJPA.setAddedDate(dateNow);
        employeeJPA.setDatasource("MariaDB");

        EmployeeJPA addedEmployee = null;

        if(!(employeeJPA.isFirstNameEmpty() || employeeJPA.isLastNameEmpty() || employeeJPA.isAddedDateEmpty() || employeeJPA.isEmploymentStatusEmpty())) {
            addedEmployee = employeeJPARepository.save(employeeJPA);
        }

        return addedEmployee;
    }

    private EmployeeMongo addEmployeeMongo(EmployeeMongo employeeMongo) {
        String dateNow = new Date().dateNowToIso();
        employeeMongo.setAddedDate(dateNow);
        employeeMongo.setDatasource("MongoDB");

        EmployeeMongo addedEmployee = null;

        if(!(employeeMongo.isFirstNameEmpty() || employeeMongo.isLastNameEmpty() || employeeMongo.isAddedDateEmpty() || employeeMongo.isEmploymentStatusEmpty())) {
            addedEmployee = employeeMongoRepository.save(employeeMongo);
        }

        return addedEmployee;
    }

    private EmployeeMongo toEmployeeMongo(EmployeeJPA employeeJPA) {
        EmployeeMongo employeeMongo = new EmployeeMongo();
        employeeMongo.setFirstName(employeeJPA.getFirstName());
        employeeMongo.setLastName(employeeJPA.getLastName());
        employeeMongo.setAddedDate(employeeJPA.getAddedDate());
        employeeMongo.setEmploymentStatus(employeeJPA.getEmploymentStatus());
        employeeMongo.setDatasource(employeeJPA.getDatasource());

        return employeeMongo;
    }
}
