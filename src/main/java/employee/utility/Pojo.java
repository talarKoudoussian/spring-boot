package employee.utility;

import employee.data.EmployeeJPA;
import employee.data.EmployeeMongo;

public class Pojo {

    public EmployeeMongo toEmployeeMongo(EmployeeJPA employeeJPA) {
        EmployeeMongo employeeMongo = new EmployeeMongo();
        employeeMongo.setFirstName(employeeJPA.getFirstName());
        employeeMongo.setLastName(employeeJPA.getLastName());
        employeeMongo.setAddedDate(employeeJPA.getAddedDate());
        employeeMongo.setEmploymentStatus(employeeJPA.getEmploymentStatus());
        employeeMongo.setDatasource(employeeJPA.getDatasource());

        return employeeMongo;
    }
}
