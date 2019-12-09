package com.vea.is.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	
	List<Person> findByLoginNameAndPassword(String loginName, String password);

}
