package com.vea.is.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vea.is.entities.Subject;
import com.vea.is.repositories.SubjectRepository;

@Service
public class SubjectService {

	private static final Logger log = LoggerFactory.getLogger(SubjectService.class);

	@Autowired
	private SubjectRepository subjectRepository;


	public Subject save(Subject person) {
		return subjectRepository.save(person);
	}

	public List<Subject> findAll() {
		return (List<Subject>)subjectRepository.findAll();
	}
}
