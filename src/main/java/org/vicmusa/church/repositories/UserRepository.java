package org.vicmusa.church.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vicmusa.church.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	User findByUserName(String userName);

	User findByForgotPasswordCode(String forgotPasswordCode);
	
	Page<User> findAll(Pageable pageable);
	
}
