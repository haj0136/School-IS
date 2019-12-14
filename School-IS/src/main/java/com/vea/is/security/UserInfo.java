package com.vea.is.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vea.is.entities.Person;
import com.vea.is.entities.Student;
import com.vea.is.entities.Teacher;

public class UserInfo implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4738785789329697988L;
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfo(Person user) {
		authorities = new ArrayList<>();
		this.userName = user.getLoginName();
		this.password = user.getPassword();
		
		if(user instanceof Teacher) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (user instanceof Student) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}
	
	public UserInfo() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
