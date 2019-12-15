package com.vea.is.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vea.is.entities.Subject;
import com.vea.is.entities.Teacher;
import com.vea.is.services.SubjectService;
import com.vea.is.services.TeacherService;

@Component
public class StringToTeacherConverter implements Converter<String, Teacher> {

	@Autowired
	private TeacherService teacherService;

	@Override
	public Teacher convert(String source) {
		long id = Long.valueOf(source).longValue();
		var sub = teacherService.findById(id);
		return sub;
	}

}
