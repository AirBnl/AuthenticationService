package com.airbnl.airbnlauthentication.controller;

import com.airbnl.airbnlauthentication.model.User;
import com.airbnl.airbnlauthentication.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping
    public String signIn(User user) {
        return user.getUsername();
    }

    @PostMapping
    public String signUp(User user, Model model) {
        userService.save(user);
        return user.getPassword();
    }
}
