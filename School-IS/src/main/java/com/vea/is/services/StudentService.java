package com.vea.is.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vea.is.entities.Person;
import com.vea.is.entities.Student;
import com.vea.is.repositories.StudentRepository;

@Service
public class StudentService {

private static final Logger log = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public Student save(Student person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		return studentRepository.save(person);
	}

	public List<Student> findAll() {
		return (List<Student>)studentRepository.findAll();
	}

	public Person findById(long id) {
		return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
	}
}
