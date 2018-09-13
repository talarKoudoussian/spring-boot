package employee.service;

import employee.data.EmployeeJPA;
import employee.data.EmployeeMongo;
import employee.repository.EmployeeJPARepository;
import employee.repository.EmployeeMongoRepository;
import employee.utility.Date;
import employee.utility.Pojo;
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

    Pojo pojoUtil;

    @Override
    public Object getEmployee(String companyId, String employeeId, int version) {
        Object returnEmployee = null;

        switch (version) {
            case 1: {
                returnEmployee = getEmployeeJPA(companyId, employeeId);
                break;
            }
            case 2:
            default: {
                returnEmployee = getEmployeeMongo(employeeId);
                break;
            }
        }

        return returnEmployee;
    }

    @Override
    public List<Object> getAllEmployees(String companyId, int version) {
        List<Object> employeesList = new ArrayList<>();

        switch (version) {
            case 1: {
                Iterable<EmployeeJPA> employeesJPA = getEmployeesJPA(companyId);
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

    private Iterable<EmployeeJPA> getEmployeesJPA(String companyId) {
        Iterable<EmployeeJPA> employees = null;

        if(companyId.matches("\\d+")) {
            Long cid = Long.valueOf(companyId);
            employees = employeeJPARepository.getEmployees(cid);
        }

        return employees;
    }

    @Override
    public Object addEmployee(String companyId, EmployeeJPA employeeJPA, int version) {
        Object addedEmployee = null;

        switch (version) {
            case 1: {
                addedEmployee = addEmployeeJPA(companyId, employeeJPA);
                break;
            }

            case 2:
            default: {
                EmployeeMongo employeeMongo = pojoUtil.toEmployeeMongo(employeeJPA);
                addedEmployee = addEmployeeMongo(employeeMongo);
                break;
            }
        }

        return addedEmployee;
    }

    @Override
    public Object updateEmployee(String companyId, String employeeId, EmployeeJPA employee, int version) {
        Object updatedEmployee = null;

        switch (version) {
            case 1: {
                updatedEmployee = updateEmployeeJPA(companyId, employeeId, employee);
                break;
            }
            case 2:
            default: {
                updatedEmployee = updateEmployeeMongo(employeeId, employee);
                break;
            }
        }

        return updatedEmployee;
    }

    @Override
    public Object deleteEmployee(String companyId, String employeeId, int version) {
        Object deletedEmployee = null;

        switch (version) {
            case 1: {
                deletedEmployee = deleteEmployeeJPA(companyId, employeeId);
                break;
            }
            case 2:
            default: {
                deletedEmployee = deleteEmployeeMongo(employeeId);
                break;
            }
        }

        return deletedEmployee;
    }

    @Override
    public Object updatePartialEmployee(String companyId, String employeeId, EmployeeJPA partialEmployeeJPA, int version) {
        Object updatedEmployee = null;

        switch (version) {
            case 1: {
                updatedEmployee = updatePartialEmployeeJPA(companyId, employeeId, partialEmployeeJPA);
                break;
            }
            case 2:
            default: {
                EmployeeMongo partialEmployeeMongo = pojoUtil.toEmployeeMongo(partialEmployeeJPA);
                updatedEmployee = updatePartialEmployeeMongo(employeeId, partialEmployeeMongo);
                break;
            }
        }

        return updatedEmployee;
    }

    private List<EmployeeMongo> getAllEmployeesMongo() {
        List<EmployeeMongo> employees = employeeMongoRepository.findAll();
        return employees;
    }

    private EmployeeJPA updatePartialEmployeeJPA(String companyId, String employeeId, EmployeeJPA partialEmployeeJPA) {
        EmployeeJPA updatedEmployee = null;

        if (employeeId.matches("\\d+")) {
            Long empId = Long.valueOf(employeeId);
            Long cid = Long.valueOf(companyId);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.getEmployee(cid, empId);

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

    private EmployeeJPA getEmployeeJPA(String companyId, String employeeId) {
        EmployeeJPA selectedEmployee = null;

        if (employeeId.matches("\\d+")) {
            Long empId = Long.valueOf(employeeId);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.getEmployee(Long.valueOf(companyId), empId);

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

    private EmployeeJPA deleteEmployeeJPA(String companyId, String employeeId) {
        EmployeeJPA deletedEmployee = null;

        if(employeeId.matches("\\d+")){
            Long empId = Long.valueOf(employeeId);
            Long cid = Long.valueOf(companyId);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.getEmployee(cid, empId);

            if(employeeOpt.isPresent()) {
                employeeJPARepository.deleteById(empId);
                deletedEmployee = employeeOpt.get();
            }
        }

        return deletedEmployee;
    }

    private EmployeeJPA updateEmployeeJPA(String companyId, String employeeId, EmployeeJPA employeeJPA) {
        EmployeeJPA updatedEmployee = null;

        if (employeeId.matches("\\d+")) {
            Long empId = Long.valueOf(employeeId);
            Long cid = Long.valueOf(companyId);
            Optional<EmployeeJPA> employeeOpt = employeeJPARepository.getEmployee(cid, empId);

            if (employeeOpt.isPresent()) {

                if(!(employeeJPA.isFirstNameEmpty() || employeeJPA.isLastNameEmpty() || employeeJPA.isEmploymentStatusEmpty())) {
                    EmployeeJPA emp = employeeOpt.get();
                    emp.setFirstName(employeeJPA.getFirstName());
                    emp.setLastName(employeeJPA.getLastName());
                    emp.setEmploymentStatus(employeeJPA.getEmploymentStatus());
                    emp.setCompanyId(cid);
                    updatedEmployee = employeeJPARepository.save(emp);
                }
            }
        }

        return updatedEmployee;
    }

    private EmployeeMongo updateEmployeeMongo(String id, EmployeeJPA employeeJPA) {
        EmployeeMongo employeeMongo = pojoUtil.toEmployeeMongo(employeeJPA);
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

    private EmployeeJPA addEmployeeJPA(String companyId, EmployeeJPA employeeJPA) {
        String dateNow = new Date().dateNowToIso();
        employeeJPA.setAddedDate(dateNow);
        employeeJPA.setDatasource("MariaDB");
        employeeJPA.setCompanyId(Long.valueOf(companyId));

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
}
