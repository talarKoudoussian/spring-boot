package models;

public class Employee {

  private long employeeId;
  private String firstName;
  private String lastName;
  private String addedDate;
  private String employmentStatus;

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

  public String getStatus() {
    return employmentStatus;
  }

  public void setStatus(String employmentStatus) {
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
