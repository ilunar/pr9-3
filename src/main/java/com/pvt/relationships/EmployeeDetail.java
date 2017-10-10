package com.pvt.relationships;

public class EmployeeDetail implements java.io.Serializable {
    private Integer employeeId;
    private Employee employee;
    private String street;
    private String sity;
    private String state;
    private String country;
    
    public Integer getEmployeeId() {return employeeId;}
    public void setEmployeeId(Integer employeeId) {this.employeeId = employeeId;}
    public Employee getEmployee() {return employee;}
    public void setEmployee(Employee employee) {this.employee = employee;}
    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}
    public String getSity() {return sity;}
    public void setSity(String sity) {this.sity = sity;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
    
    public String toString(){
        return "\tstreet="+this.getStreet()+" sity="+this.getSity()
                +" state="+this.getState()+" country="+this.getCountry();
    }
}
