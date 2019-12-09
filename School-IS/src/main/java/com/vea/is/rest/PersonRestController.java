package com.vea.is.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vea.is.entities.Person;
import com.vea.is.repositories.PersonRepository;
import com.vea.is.services.PersonService;

@RestController
public class PersonRestController {
    
    private final PersonService personService;
    
    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }
    
    @GetMapping("/rest/persons")
    public List<Person> getAll(){
        List<Person> persons = personService.findAll();
        return persons;
    }
}
