package com.softarex.questinary.controller;

import com.softarex.questinary.DTO.UserDTO;
import com.softarex.questinary.entity.JWTResponse;
import com.softarex.questinary.entity.Pass;
import com.softarex.questinary.entity.User;
import com.softarex.questinary.service.RegistrationService;
import com.softarex.questinary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserService userService;

    @PostMapping("registrate")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        User user = userService.convertFromDTO(userDTO);
        registrationService.register(user);
        return ResponseEntity.ok("Registered");
    }

    @PutMapping("editUser")
    public ResponseEntity<String> editUser(@RequestBody UserDTO userDTO, Principal principal) {
        User user = userService.convertFromDTO(userDTO);
        userService.editUser(user, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("editPassword")
    public ResponseEntity<String> editUser(@RequestBody Pass passwords, Principal principal) {
        userService.editUserPassword(passwords, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("getUser")
    public User getUser(Principal principal) {
        User user = userService.getByLogin(principal.getName());
        return user;
    }

    @GetMapping("getUsersId")
    public List<Long> getUsersId() {
        return userService.getUsersId();
    }
}
