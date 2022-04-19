package com.softarex.questinary.controller;

import com.softarex.questinary.DTO.FieldDTO;
import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.FieldType;
import com.softarex.questinary.entity.User;
import com.softarex.questinary.exception.NotFoundException;
import com.softarex.questinary.service.FieldService;
import com.softarex.questinary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

@Slf4j
@RestController
@RequestMapping("field")
public class FieldController {

    @Autowired
    FieldService fieldService;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody FieldDTO fieldDTO, Principal principal) {
        User user = userService.getByLogin(principal.getName());
        fieldService.saveFromDto(fieldDTO, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        fieldService.removeField(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody FieldDTO fieldDTO, Principal principal) {
        User user = userService.getByLogin(principal.getName());
        fieldService.updateFromDto(fieldDTO, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<FieldDTO> get(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Principal principal) {
        User user = userService.getByLogin(principal.getName());
        return fieldService.findByUser(pageable, user);
    }

    @GetMapping("{id}")
    public FieldDTO getOne(@PathVariable Long id) {
        return fieldService.findById(id);
    }
}