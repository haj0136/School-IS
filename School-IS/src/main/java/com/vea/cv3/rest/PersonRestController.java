package com.vea.cv3.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vea.cv3.entities.Person;
import com.vea.cv3.repositories.IPersonRepository;

@RestController
public class PersonRestController {
    
    private final IPersonRepository personRepository;
    
    @Autowired
    public PersonRestController(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    @GetMapping("/rest/persons")
    public List<Person> getAll(){
        List<Person> persons = (List<Person>) personRepository.findAll();
        return persons;
    }
}
