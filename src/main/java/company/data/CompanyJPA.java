package company.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class CompanyJPA {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long companyId;

    @Column(name="name")
    private String name;

    @Column(name="location")
    private String location;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="added_date")
    private String addedDate;

    @Column(name="status")
    private Boolean status;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddedDate() { return addedDate; }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public Boolean getStatus() { return status; }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    @JsonIgnore
    public boolean isNameEmpty() {
        if(name == null || name == "") {
            return true;
        }

        return false;
    }

    @JsonIgnore
    public boolean isLocationEmpty() {
        if(location == null || location == "") {
            return true;
        }

        return false;
    }

    @JsonIgnore
    public boolean isPhoneNumberEmpty() {
        if(phoneNumber == null || phoneNumber == "") {
            return true;
        }

        return false;
    }

    @JsonIgnore
    public boolean isAddedDateEmpty() {
        if(addedDate == null || addedDate == "") {
            return true;
        }

        return false;
    }

    @JsonIgnore
    public boolean isStatusEmpty() {
        return status == null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Company[name: ");
        sb.append(name);
        sb.append(", location: ");
        sb.append(location);
        sb.append(", phoneNumber: ");
        sb.append(phoneNumber);
        sb.append(", addedDate: ");
        sb.append(addedDate);
        sb.append(", status: ");
        sb.append(status);
        sb.append("]");
        return sb.toString();
    }

}
