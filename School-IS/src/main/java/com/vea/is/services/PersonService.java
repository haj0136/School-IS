package com.vea.is.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vea.is.entities.Person;
import com.vea.is.repositories.PersonRepository;

@Service
public class PersonService {
	
	private static final Logger log = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person save(Person person) {
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
	
	public Person findByLoginAndPassword(String login, String password) {
		List<Person> persons = personRepository.findByLoginNameAndPassword(login, password);
		
		
		if(persons != null && !persons.isEmpty()) {
			if(persons.size() > 1) {
				log.warn("More users with same name and password !!");
			}
			return persons.get(0);
		} else {
			return null;
		}
	}
	
	
}
