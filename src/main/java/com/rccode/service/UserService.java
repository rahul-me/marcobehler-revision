package com.rccode.service;

import com.rccode.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    public User findById(String id) {
        String randomName = UUID.randomUUID().toString();
        // always "finds" the user, every user has a random name
        return new User(id, randomName);
    }
}
