package com.vea.is.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

import javax.persistence.Id;

@MappedSuperclass
public class SchoolEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -360923379568765790L;
	
	@Id
	// It looks like H2 DB does not support identity column
	//switch to GenerationType.Identity with others DB's
	@GeneratedValue(strategy= GenerationType.AUTO) 
	protected Long id;
	
	
	public SchoolEntity(){
		super();
		id = 0L;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolEntity other = (SchoolEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
	
