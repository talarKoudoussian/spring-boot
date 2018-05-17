package com.sample;


public class SbEmployee {

  private long employeeId;
  private String firstName;
  private String lastName;
  private java.sql.Timestamp addedDate;
  private long status;


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


  public java.sql.Timestamp getAddedDate() {
    return addedDate;
  }

  public void setAddedDate(java.sql.Timestamp addedDate) {
    this.addedDate = addedDate;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
