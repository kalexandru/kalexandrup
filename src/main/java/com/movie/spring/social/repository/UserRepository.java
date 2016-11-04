package com.movie.spring.social.repository;

import com.movie.spring.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
