package employee.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "employees")
public class EmployeeMongo {

    @Id
    private String _id;

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
        return _id;
    }

    public void setEmployeeId(String employeeId) {
        this._id = employeeId;
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

    @JsonIgnore
    public boolean isDatasourceEmpty() {
        return datasource == null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Employee[id: ");
        sb.append(_id);
        sb.append(", firstName: ");
        sb.append(firstName);
        sb.append(", lastName: ");
        sb.append(lastName);
        sb.append(", addedDate: ");
        sb.append(addedDate);
        sb.append(", employmentStatus: ");
        sb.append(employmentStatus);
        sb.append(", datasource: ");
        sb.append(datasource);
        sb.append("]");
        return sb.toString();

    }
}
