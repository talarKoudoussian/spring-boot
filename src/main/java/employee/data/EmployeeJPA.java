package employee.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "employee")
public class EmployeeJPA {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="employee_id")
    private Long employeeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="added_date")
    private String addedDate;

    @Column(name="employment_status")
    private Boolean employmentStatus;

    @Column(name="datasource")
    private String datasource;

    @Column(name = "company_id")
    private Long companyId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonIgnore
    public boolean isFirstNameEmpty() {
        if(firstName == null || firstName == "") {
            return true;
        }

        return false;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public boolean isLastNameEmpty() {
        if(lastName == null || lastName == "") {
            return true;
        }

        return false;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    @JsonIgnore
    public boolean isAddedDateEmpty() {
        if(addedDate == null || addedDate == "") {
            return true;
        }

        return false;
    }

    public Boolean getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Boolean employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getDatasource() {
        return this.datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    @JsonIgnore
    public boolean isEmploymentStatusEmpty() {
        return employmentStatus == null;
    }

    @JsonIgnore
    public boolean isDatasourceEmpty() {
        if(datasource == null || datasource == "") {
            return true;
        }

        return false;
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
        sb.append(", datasource: ");
        sb.append(datasource);
        sb.append(", companyId: ");
        sb.append(companyId);
        sb.append("]");
        return sb.toString();
    }
}
