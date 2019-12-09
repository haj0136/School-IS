package com.vea.is.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Subject extends SchoolEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2320418355777591704L;
	
	
	protected String name;
	protected String code;
	protected int credits;
	protected String optionality;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="subject")
	protected List<Lesson> lessons; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getOptionality() {
		return optionality;
	}
	public void setOptionality(String optionality) {
		this.optionality = optionality;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", code=" + code + ", credits=" + credits + ", optionality="
				+ optionality + "]";
	}
}
