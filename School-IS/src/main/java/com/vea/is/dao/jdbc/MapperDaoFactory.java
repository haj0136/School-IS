package com.vea.is.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vea.is.dao.DaoFactory;
import com.vea.is.dao.ISubjectRepository;

@Component
public class MapperDaoFactory implements DaoFactory {

	@Autowired
	private JdbcSubjectRepository subjectDao;

	@Override
	public ISubjectRepository getSubjectDao() {
		return subjectDao;
	}
}
