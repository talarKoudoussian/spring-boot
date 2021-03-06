package employee.service;

import employee.data.EmployeeJPA;

import java.util.List;

public interface EmployeeService {

    public Object getEmployee(String id, int version);
    public Object addEmployee(EmployeeJPA employee, int version);
    public Object updateEmployee(String id, EmployeeJPA employee, int version);
    public Object deleteEmployee(String id, int version);
    public Object updatePartialEmployee(String id, EmployeeJPA employee, int version);
    public List<Object> getAllEmployees(int version);
}
