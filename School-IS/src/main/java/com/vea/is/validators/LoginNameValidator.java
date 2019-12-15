package com.vea.is.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vea.is.entities.Person;
import com.vea.is.services.PersonService;

public class LoginNameValidator implements ConstraintValidator<UniqueLoginName, Person> {

	@Autowired
	private PersonService personService;


	@Override
	public boolean isValid(Person value, ConstraintValidatorContext context) {
		if(personService == null) {
			return true;
		}
		if(value.getLoginName().isBlank()) {
			return false;
		}

		if(value.getId() != 0) {
			return true;
		}

		var p = personService.findByLogin(value.getLoginName());
		if (p == null) {
			return true;
		}
		return false;
	}

}
