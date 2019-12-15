package com.vea.is.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vea.is.dao.DaoFactory;
import com.vea.is.dao.ISubjectRepository;
import com.vea.is.dao.entities.Subject;

@Service
public class SubjectService {

	private static final Logger log = LoggerFactory.getLogger(SubjectService.class);

	private ISubjectRepository subjectRepository;

	@Autowired
	public SubjectService(DaoFactory factory) {
		subjectRepository = factory.getSubjectDao();
	}

	public Subject save(Subject sub) {
		long id = subjectRepository.save(sub);
		return findById(id);
	}

	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}

	public Subject findById(long id) {
		return subjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subject Id:" + id));
	}

	public void delete(Subject sub) {
		subjectRepository.deleteById(sub.getId());
	}
}
