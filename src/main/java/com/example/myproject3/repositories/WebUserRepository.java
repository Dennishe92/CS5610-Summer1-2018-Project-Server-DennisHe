package com.example.myproject3.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myproject3.model.WebUser;

public interface WebUserRepository extends CrudRepository<WebUser, Integer> {
	
	@Query("SELECT u FROM WebUser u WHERE u.username=:username AND u.password=:password")
	WebUser findUserByUsernameAndPassword(
			@Param("username") String username,
			@Param("password") String password);
	
	@Query("SELECT u FROM WebUser u WHERE u.username=:username")
	WebUser findUserByUsername(
			@Param("username") String username);
}
