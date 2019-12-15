package com.vea.is.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vea.is.dao.entities.Subject;
import com.vea.is.services.SubjectService;

@Component
public class StringToSubjectConverter implements Converter<String, Subject> {

	@Autowired
	private SubjectService subjectService;

	@Override
	public Subject convert(String source) {
		long id = Long.valueOf(source).longValue();
		var sub = subjectService.findById(id);
		return sub;
	}

}
