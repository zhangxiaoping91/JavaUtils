package com.xx.bean;

import java.io.Serializable;

public class Student implements Serializable,Comparable<Student> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String age;
	private String sex;
	
	public Student(){}
	public Student(String name,String age,String sex)
	{
		this.name=name;
		this.age=age;
		this.sex=sex;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	@Override
	public int compareTo(Student o) {
		if(o!=null)
		{
			if(Double.parseDouble(this.getAge())>Double.parseDouble(o.getAge()))
					return 1;
			else if(Double.parseDouble(this.getAge())==Double.parseDouble(o.getAge()))
					return 0;
		}
		return -1;
	}
	
	
	
	

}
