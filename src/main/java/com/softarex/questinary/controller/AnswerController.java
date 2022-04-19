package com.softarex.questinary.controller;

import com.softarex.questinary.DTO.AnswerDTO;
import com.softarex.questinary.DTO.FieldDTO;
import com.softarex.questinary.entity.Answer;
import com.softarex.questinary.entity.User;
import com.softarex.questinary.service.AnswerService;
import com.softarex.questinary.service.FieldService;
import com.softarex.questinary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    FieldService fieldService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;

    @GetMapping("question/{id}")
    public List<FieldDTO> question(@PathVariable Long id) {
        List<FieldDTO> fields = fieldService.findActiveByAskerId(id);
        return fields;
    }

    // @MessageMapping("/hello")
    //@SendTo("/answers")
    @PostMapping("/add_answer/{id}")
    public ResponseEntity<String> addAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable Long id) {
        answerService.addAnswer(answerDTO, id);
        User user = userService.getById(id);
        messagingTemplate.convertAndSend("/answers", "update");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get_answers")
    public Page<AnswerDTO> get(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Principal principal) {
        User user = userService.getByLogin(principal.getName());
        return answerService.findByUser(pageable, user);
    }
}
