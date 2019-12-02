package com.vea.cv3.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.cv3.entities.Person;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Long> {

}
