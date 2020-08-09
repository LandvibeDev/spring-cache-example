package com.landvibe.cacheexample;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUser(@PathVariable int uid) {
        return userService.getUser(uid);
    }
}
