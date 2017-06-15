package com.biraj.springmysql;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public default boolean findbyusernameandpassword(User user){
		
		return false;
	}

}
