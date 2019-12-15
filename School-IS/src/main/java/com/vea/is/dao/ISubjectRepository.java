package com.vea.is.dao;

import java.util.List;
import java.util.Optional;

import com.vea.is.dao.entities.Subject;

public interface ISubjectRepository {

	List<Subject> findAll();

	Optional<Subject> findById(Long id);

	int save(Subject subject);

    int deleteById(Long id);
}
