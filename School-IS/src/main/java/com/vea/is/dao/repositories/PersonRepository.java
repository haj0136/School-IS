package com.vea.is.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.dao.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	
	Optional<Person> findByLoginName(String loginName);

}
