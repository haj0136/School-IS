package com.vea.is.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vea.is.entities.Person;
import com.vea.is.repositories.PersonRepository;

@RestController
public class PersonRestController {
    
    private final PersonRepository personRepository;
    
    @Autowired
    public PersonRestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    @GetMapping("/rest/persons")
    public List<Person> getAll(){
        List<Person> persons = (List<Person>) personRepository.findAll();
        return persons;
    }
}
