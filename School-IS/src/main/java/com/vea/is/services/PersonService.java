package com.vea.is.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vea.is.entities.Person;
import com.vea.is.repositories.PersonRepository;

@Service("personService")
public class PersonService {

	private static final Logger log = LoggerFactory.getLogger(PersonService.class);

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Person save(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		return personRepository.save(person);
	}

	public List<Person> findAll() {
		return (List<Person>)personRepository.findAll();
	}

	public Person findById(long id) {
		return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
	}

	public void delete(Person person) {
		personRepository.delete(person);
	}

	public Person findByLogin(String login) {
		Optional<Person> op = personRepository.findByLoginName(login);

		if(op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}


}
