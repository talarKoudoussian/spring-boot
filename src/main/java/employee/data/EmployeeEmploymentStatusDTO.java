package employee.data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class EmployeeEmploymentStatusDTO {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="employee_id")
    private long employeeId;

    @Column(name = "employment_status")
    private boolean employmentStatus;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public boolean getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(boolean employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

}
