package com.hoaxify.ws.user;

import com.hoaxify.ws.error.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
   // private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/api/1.0/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody User user){
        String username = user.getUsername();
        if (username == null || username.isEmpty()) {
            ApiError apiError = new ApiError(400, "Validation Error", "/api/1.0/users");
            Map<String, String> validationErros = new HashMap<>();
            validationErros.put("username", "username cannot be null");
            apiError.setValidationErrors(validationErros);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        }
        userService.createUser(user);
        return ResponseEntity.ok("user created");
    }
}
