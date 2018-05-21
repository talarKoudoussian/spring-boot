package employee;

import javax.persistence.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="employee_id")
    private long employeeId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="added_date")
    private String addedDate;
    @Column(name="employment_status")
    private boolean employmentStatus;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public Boolean getStatus() {
        return employmentStatus;
    }

    public void setStatus(boolean employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Employee[firstName: ");
        sb.append(this.firstName);
        sb.append(" lastName: ");
        sb.append( this.lastName);
        sb.append(" addedDate: ");
        sb.append(this.addedDate);
        sb.append(" employmentStatus: ");
        sb.append(this.employmentStatus);
        sb.append( " ]");
        return sb.toString();
    }
}
