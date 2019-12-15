package com.vea.is.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import com.vea.is.dao.DaoFactory;
import com.vea.is.dao.ISubjectRepository;

//@Component
public class JpaDaoFactory implements DaoFactory {

	@Autowired
	private SubjectDaoJpa subjectDao;

	@Override
	public ISubjectRepository getSubjectDao() {
		return subjectDao;
	}
}
