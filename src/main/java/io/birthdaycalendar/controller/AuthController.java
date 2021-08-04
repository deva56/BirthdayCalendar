package io.birthdaycalendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.birthdaycalendar.constants.DatabaseState;
import io.birthdaycalendar.constants.LoginState;
import io.birthdaycalendar.dto.LoginRequest;
import io.birthdaycalendar.dto.RegisterRequest;
import io.birthdaycalendar.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        DatabaseState result = authService.signup(registerRequest);
        if (result.equals(DatabaseState.USER_OK)) {
            return new ResponseEntity<>("User Registration Successfull", HttpStatus.OK);
        } else if(result.equals(DatabaseState.USER_WITH_SAME_EMAIL_EXISTS)){
            return new ResponseEntity<>("User With Same Email Already Exists", HttpStatus.CONFLICT);
        }
        else {
        	return new ResponseEntity<>("User With Same Username Already Exists", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    	LoginState result = authService.login(loginRequest);
    	if(result.equals(LoginState.LOGIN_OK)) {
    		return new ResponseEntity<>(loginRequest.getUsername(), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>("Wrong Login Credentials", HttpStatus.FORBIDDEN);
    	}
    }
}
