package com.vea.is.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vea.is.dao.entities.Subject;

public class SubjectMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
		Subject s = new Subject();
		s.setId(rs.getLong("id"));
        s.setName(rs.getString("name"));
        s.setCode(rs.getString("code"));
        s.setCredits(rs.getInt("credits"));
        s.setOptionality(rs.getString("optionality"));

		return s;
	}

}
