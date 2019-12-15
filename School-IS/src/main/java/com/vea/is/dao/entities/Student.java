package com.vea.is.dao.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Student extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7472998272082470569L;
	
	protected String fieldOfStudy;
	protected float grade;
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	protected List<Lesson> lessons;
	
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", email=" + email
				+ ", fieldOfStudy=" + fieldOfStudy + ", grade=" + grade + "]";
	}
}
