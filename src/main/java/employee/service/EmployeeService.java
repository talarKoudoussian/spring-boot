package employee.service;

import employee.data.EmployeeJPA;

import java.util.List;

public interface EmployeeService {
    List<Object> getAllEmployees(String companyId, int version);
    Object getEmployee(String companyId, String employeeId, int version);
    Object addEmployee(String companyId, EmployeeJPA employee, int version);
    Object deleteEmployee(String companyId, String employeeId, int version);
    Object updateEmployee(String companyId, String employeeId, EmployeeJPA employee, int version);
    Object updatePartialEmployee(String companyId, String employeeId, EmployeeJPA employee, int version);
}
