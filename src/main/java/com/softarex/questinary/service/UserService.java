package com.softarex.questinary.service;

import com.softarex.questinary.DTO.UserDTO;
import com.softarex.questinary.entity.Pass;
import com.softarex.questinary.entity.Role;
import com.softarex.questinary.entity.User;
import com.softarex.questinary.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getByLogin(String login) {

        User user = userRepository.findByLogin(login);
        return user;
    }

    public User getById(Long id) {

        User user = userRepository.findById(id).get();
        return user;
    }

    public User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        return user;
    }

    public void editUser(User user, String login) {
        User oldUser = userRepository.findByLogin(login);
        if (!Objects.equals(user.getLogin(), oldUser.getLogin()) || !Objects.equals(user.getId(), oldUser.getId())) {
            throw new BadCredentialsException("FailedToChange");
        }
        user.setRoles(oldUser.getRoles());
        userRepository.save(user);
    }

    //todo transactional?
    public void addUser(User user) {
        userRepository.save(user);
    }

    public void addUserFromDto(UserDTO userDTO) {
        User user = convertFromDTO(userDTO);
        userRepository.save(user);
    }

    public List<Long> getUsersId() {
        return userRepository.findAll().stream().map((User::getId)).collect(Collectors.toList());
    }

    public void editUserPassword(Pass passwords, String login) {
        User user = userRepository.findByLogin(login);
        if (!Objects.equals(passwords.getCurrentPassword(), user.getPassword()) && passwords.getNewPassword() != null) {
            throw new BadCredentialsException("Incorrect password");
        }
        user.setPassword(passwords.getNewPassword());
        userRepository.save(user);
    }
}
