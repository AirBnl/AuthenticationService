package com.airbnl.airbnlauthentication.service.interfaces;

import com.airbnl.airbnlauthentication.model.User;

import java.util.List;

public interface IUserService {
    User save(User hotel);
    User getByUsername(String username);
}
