package com.airbnl.airbnlauthentication.controller;

import com.airbnl.airbnlauthentication.model.User;
import com.airbnl.airbnlauthentication.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping("/signIn")
    public User signIn(@RequestBody User user) {
        return userService.getByUsername(user.getUsername());
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user) {
        return userService.save(user);
    }
}
