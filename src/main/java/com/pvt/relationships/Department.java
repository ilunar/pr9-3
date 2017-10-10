package com.pvt.relationships;

import java.util.*;

public class Department implements java.io.Serializable {
    private Integer departmentId;
    private String departmentName;
    private Set<Employee> employees=new HashSet<Employee>(0);
    
    public Integer getDepartmentId() {return departmentId;}
    public void setDepartmentId(Integer departmentId) {this.departmentId = departmentId;}
    public String getDepartmentName() {return departmentName;}
    public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}
    public Set<Employee> getEmployees() {return employees;}
    public void setEmployees(Set<Employee> employees) {this.employees = employees;}
    
    public String toString(){
        return "\tdepartmentId="+this.getDepartmentId()+" departmentName="+this.getDepartmentName();
    }
}
