package io.birthdaycalendar.service;

import io.birthdaycalendar.constants.DatabaseState;
import io.birthdaycalendar.constants.LoginState;
import io.birthdaycalendar.dto.LoginRequest;
import io.birthdaycalendar.dto.RegisterRequest;
import io.birthdaycalendar.models.User;
import io.birthdaycalendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
	}

	public DatabaseState signup(RegisterRequest registerRequest) {
		User user = new User(registerRequest.getUsername(), 
				registerRequest.getEmail(),
				passwordEncoder.encode(registerRequest.getPassword()),
				Instant.now());
		
		User userInDb = userRepository.findByEmailOrUsername(registerRequest.getEmail(), registerRequest.getUsername());
		if(userInDb == null)
		{
			userRepository.save(user);
			return DatabaseState.USER_OK;
		}
		else if(userInDb.getEmail().equals(registerRequest.getEmail())) {
			return DatabaseState.USER_WITH_SAME_EMAIL_EXISTS;
		}
		else {
			return DatabaseState.USER_WITH_SAME_USERNAME_EXISTS;
		}
	}
	
	public LoginState login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			 SecurityContextHolder.getContext().setAuthentication(authentication); 
			return LoginState.LOGIN_OK;
		}
		else {
			return LoginState.WRONG_ENTRY;
		}
	}
}
