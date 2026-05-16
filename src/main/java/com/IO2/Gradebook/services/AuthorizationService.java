package com.IO2.Gradebook.services;

import com.IO2.Gradebook.dto.UserPublicData;
import com.IO2.Gradebook.exceptions.InvalidLoginException;
import com.IO2.Gradebook.misc.LoginData;
import com.IO2.Gradebook.models.User;
import com.IO2.Gradebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private UserRepository userRepository;

    public UserPublicData login(LoginData loginData) {
        User user = userRepository.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());

        if (user == null) {
            throw new InvalidLoginException("Invalid email or password");
        }

        return new UserPublicData(user);
    }
}
