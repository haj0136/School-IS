package com.vea.is.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vea.is.dao.entities.Person;
import com.vea.is.services.PersonService;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	PersonService personService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person user = personService.findByLogin(username);
		if(user != null) {
			return new UserInfo(user);
		} else {
			throw new UsernameNotFoundException("Username: " + username + " does not exist!");
		}
	}

}
