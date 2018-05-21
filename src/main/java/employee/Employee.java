package employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import java.text.SimpleDateFormat;
//import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long employee_id;
    private String first_name;
    private String last_name;
    private String added_date;
    private boolean employment_status;

//    public Employee(long employeeId, String firstName, String lastName){
//        this.employeeId = employeeId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.addedDate = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.fff").format(new Date());
//        this.employmentStatus = true;
//    }

    public Employee(){
        super();
    }

    public long getEmployeeId() {
        return employee_id;
    }

    public void setEmployeeId(long employeeId) {
        this.employee_id = employeeId;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getAddedDate() {
        return added_date;
    }

    public void setAddedDate(String addedDate) {
        this.added_date = addedDate;
    }

    public Boolean getStatus() {
        return employment_status;
    }

    public void setStatus(boolean employmentStatus) {
        this.employment_status = employmentStatus;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Employee[firstName: ");
        sb.append(this.first_name);
        sb.append(" lastName: ");
        sb.append( this.last_name);
        sb.append(" addedDate: ");
        sb.append(this.added_date);
        sb.append(" employmentStatus: ");
        sb.append(this.employment_status);
        sb.append( " ]");
        return sb.toString();
    }
}
