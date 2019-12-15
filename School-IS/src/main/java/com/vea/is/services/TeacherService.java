package com.vea.is.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vea.is.entities.Person;
import com.vea.is.entities.Teacher;
import com.vea.is.repositories.TeacherRepository;

@Service
public class TeacherService {

	private static final Logger log = LoggerFactory.getLogger(TeacherService.class);

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public Teacher save(Teacher person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		return teacherRepository.save(person);
	}

	public List<Teacher> findAll() {
		return (List<Teacher>)teacherRepository.findAll();
	}

	public Person findById(long id) {
		return teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
	}
}
