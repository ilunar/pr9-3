package com.pvt.relationships;

import java.util.*;

public class Employee implements java.io.Serializable {
    
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private EmployeeDetail employeeDetail;
    private Department department;
    private Set<Meeting>meetings=new HashSet<Meeting>(0);
    
    public Integer getEmployeeId() {return employeeId;}
    public void setEmployeeId(Integer employeeId) {this.employeeId = employeeId;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getCellPhone() {return cellPhone;}
    public void setCellPhone(String cellPhone) {this.cellPhone = cellPhone;}
    public EmployeeDetail getEmployeeDetail() {return employeeDetail;}
    public void setEmployeeDetail(EmployeeDetail employeeDetail) {this.employeeDetail = employeeDetail;}
    public Department getDepartment() {return department;}
    public void setDepartment(Department department) {this.department = department;}
    public Set<Meeting> getMeetings() {return meetings;}
    public void setMeetings(Set<Meeting> meetings) {this.meetings = meetings;}
    
    public Employee() {}
    
    public String toString(){
        return "\tId="+this.getEmployeeId()+" firstname="+this.getFirstName()
                +" lastname="+this.getLastName()+" cellphone="+this.getCellPhone()
                +"\n"+this.getDepartment().toString()
                +"\n"+this.employeeDetail.toString()+"\n";
    }
}
