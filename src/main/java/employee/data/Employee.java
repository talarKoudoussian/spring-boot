package employee.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "employee")
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
    private Boolean employmentStatus;

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

    @JsonIgnore
    public boolean isFirstNameEmpty() {
        return firstName == null;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public boolean isLastNameEmpty() {
        return lastName == null;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    @JsonIgnore
    public boolean isAddedDateEmpty() {
        return addedDate == null;
    }

    public Boolean getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Boolean employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    @JsonIgnore
    public boolean isEmploymentStatusEmpty() {
        return employmentStatus == null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Employee[firstName: ");
        sb.append(firstName);
        sb.append(", lastName: ");
        sb.append(lastName);
        sb.append(", addedDate: ");
        sb.append(addedDate);
        sb.append(", employmentStatus: ");
        sb.append(employmentStatus);
        sb.append("]");
        return sb.toString();
    }
}
