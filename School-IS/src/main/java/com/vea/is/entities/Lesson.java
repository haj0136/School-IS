package com.vea.is.entities;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Lesson extends SchoolEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5152231564885256002L;
	
	protected String classroom;
	protected LocalTime lessonTime;
	
	@ManyToOne
	protected Subject subject;
	@ManyToOne
	protected Teacher teacher;
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="lessons")
	protected List<Student> students;
	
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public LocalTime getLessonTime() {
		return lessonTime;
	}
	public void setLessonTime(LocalTime lessonTime) {
		this.lessonTime = lessonTime;
	}

}
