package com.vea.is.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	
	Optional<Person> findByLoginName(String loginName);

}
