package employee.service;

import employee.data.EmployeeJPA;

public interface EmployeeService {

    public Object getEmployee(String id, double version);
    public Object addEmployee(EmployeeJPA employee, double version);
    public Object updateEmployee(String id, EmployeeJPA employee, double version);
    public Object deleteEmployee(String id, double version);

}
