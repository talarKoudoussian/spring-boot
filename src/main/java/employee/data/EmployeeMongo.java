package employee.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "employees")
public class EmployeeMongo {

    @Id
    @Field("employeeId")
    private String employeeId;

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;

    @Field("addedDate")
    private String addedDate;

    @Field("employmentStatus")
    private Boolean employmentStatus;

    @Field("datasource")
    private String datasource;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
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
