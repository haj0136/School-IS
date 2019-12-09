package com.vea.is.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Teacher extends Person{

	/**
	 * 
	 */
	private static final long serialVersionUID = 966040243979459762L;
	
	protected int salary;
	
	protected String officeNumber;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="teacher")
	protected List<Lesson> lessons;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	@Override
	public String toString() {
		return "Teacher [salary=" + salary + ", cabinetNumber=" + officeNumber + ", name=" + name + ", surname="
				+ surname + ", phone=" + phone + ", email=" + email + ", id=" + id + "]";
	}

}
