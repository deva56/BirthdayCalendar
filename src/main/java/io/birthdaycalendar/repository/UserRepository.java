package io.birthdaycalendar.repository;

import org.springframework.data.repository.CrudRepository;

import io.birthdaycalendar.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	User findByEmail(String email);
	User findByEmailOrUsername(String email, String username);
}
