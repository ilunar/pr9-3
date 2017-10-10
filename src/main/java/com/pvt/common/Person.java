package com.pvt.common;

public class Person {

	String name;
	String address;
	int age;

    public Person() {
    }
        

    public Person(String name) {
        this.name = name;
    }
        
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
@Override
	public String toString() {
		return "name=" + name;
                
	}
        public void init(){System.out.println("метод init");}
        public void destroy(){System.out.println("метод destroy");}
}