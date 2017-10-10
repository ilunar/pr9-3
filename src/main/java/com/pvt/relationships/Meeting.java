package com.pvt.relationships;

import java.util.*;

public class Meeting {
    private Integer meetingId;
    private String topic;
    private Set<Employee> employees=new HashSet<Employee>(0);
    
    public Integer getMeetingId() {return meetingId;}
    public void setMeetingId(Integer meetingId) {this.meetingId = meetingId;}
    public String getTopic() {return topic;}
    public void setTopic(String topic) {this.topic = topic;}
    public Set<Employee> getEmployees() {return employees;}
    public void setEmployees(Set<Employee> employees) {this.employees = employees;}
    
    public String toString(){
        return "\tmeetingId="+this.getMeetingId()+" topic="+this.getTopic();
    }
}
