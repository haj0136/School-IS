package com.vea.is.dao.jdbc;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.vea.is.dao.ISubjectRepository;
import com.vea.is.dao.entities.Subject;

@Repository
public class JdbcSubjectRepository implements ISubjectRepository {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert subjectInsert;

	@Autowired
	public JdbcSubjectRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		subjectInsert = new SimpleJdbcInsert(dataSource).withTableName("SUBJECT").usingGeneratedKeyColumns("id").usingColumns("name", "code", "credits", "optionality");
	}

	@Override
	public List<Subject> findAll() {
		return jdbcTemplate.query("select * from subject", new SubjectMapper());
	}

	@Override
	public Optional<Subject> findById(Long id) {
		return Optional.of(jdbcTemplate.queryForObject("select * from subject where id = ?", new Object[] { id }, new SubjectMapper()));

	}

	@Override
	public int save(Subject subject) {
		if(subject.getId() == 0) {
			BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(subject);
			return (int) subjectInsert.executeAndReturnKey(source);
		} else {
			jdbcTemplate.update("update SUBJECT set name=?, code=?, credits=?, optionality=? where id=?",
					subject.getName(), subject.getCode(), subject.getCredits(), subject.getOptionality(), subject.getId());
			return subject.getId().intValue();
		}
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update("delete from Subject where id=?", id);
	}

}
