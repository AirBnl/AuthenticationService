package com.airbnl.airbnlauthentication.security.manager;

import com.airbnl.airbnlauthentication.model.User;
import com.airbnl.airbnlauthentication.security.SecurityConstants;
import com.airbnl.airbnlauthentication.service.implementation.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Date;

public class AuthManager {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthManager(@Lazy UserService userService, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private boolean isAuthenticated(User user) {
        String passFromDb = userService.getByUsername(user.getUsername()).getPassword();

        return bCryptPasswordEncoder.matches(passFromDb
                , user.getPassword());
    }

    public String makeToken(User user){
        if(!isAuthenticated(user))
            return "";

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET_KEY));
        return token;
    }
    public boolean isTokenValid(String token) {
        token = token.replace(SecurityConstants.BEARER , "");
        String username = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username , null, Arrays.asList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
}
