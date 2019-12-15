package com.vea.is.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vea.is.dao.ISubjectRepository;
import com.vea.is.dao.entities.Subject;

@Repository
public class SubjectDaoJpa implements ISubjectRepository {

	@Autowired
	SubjectRepository subjectRepository;

	@Override
	public List<Subject> findAll() {
		return (List<Subject>) subjectRepository.findAll();
	}

	@Override
	public Optional<Subject> findById(Long id) {
		return subjectRepository.findById(id);
	}

	@Override
	public int save(Subject subject) {
		subjectRepository.save(subject);
		return 1;
	}

	@Override
	public int deleteById(Long id) {
		subjectRepository.deleteById(id);
		return 1;
	}

}
