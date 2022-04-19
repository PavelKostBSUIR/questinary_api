package com.softarex.questinary.service;

import com.softarex.questinary.entity.User;
import com.softarex.questinary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.PageFormat;

@Service
public class RegistrationService {
    @Autowired
    private UserService userService;

    public void register(User user) {
        String login = user.getLogin();
        if (userService.getByLogin(login) != null) {
            throw new BadCredentialsException("Such user exists " + login);
        }
        userService.addUser(user);
    }
}
