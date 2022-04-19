package com.softarex.questinary.service;

import com.softarex.questinary.DTO.AnswerDTO;
import com.softarex.questinary.DTO.FieldAnswerDTO;
import com.softarex.questinary.DTO.FieldDTO;
import com.softarex.questinary.entity.Answer;
import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.FieldAnswer;
import com.softarex.questinary.entity.User;
import com.softarex.questinary.exception.NotFoundException;
import com.softarex.questinary.repo.AnswerRepository;
import com.softarex.questinary.repo.FieldAnswerRepository;
import com.softarex.questinary.repo.FieldRepository;
import com.softarex.questinary.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    @Autowired
    FieldRepository fieldRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FieldAnswerService fieldAnswerService;
    @Autowired
    UserService userService;
    @Autowired
    FieldAnswerRepository fieldAnswerRepository;

    @Transactional
    public void addAnswer(AnswerDTO answerDTO, Long id) {
        User asker = userRepository.findById(id).orElseThrow(NotFoundException::new);
        Answer answerNew = new Answer();
        answerNew.setAsker(asker);
        //todo can be error cause of saving list of entities from entity
        Answer answer = new Answer(asker, null);
        answer = answerRepository.save(answer);

        Answer finalAnswer = answer;
        List<FieldAnswer> fieldAnswers = answerDTO.getFieldAnswers().stream().map(fieldAnswerDTO -> fieldAnswerService.fromDTO(fieldAnswerDTO, finalAnswer)).collect(Collectors.toList());
        fieldAnswerRepository.saveAll(fieldAnswers);
    }

    public Page<AnswerDTO> findByUser(Pageable pageable, User user) {
        return answerRepository.findByAsker(user, pageable).map(AnswerDTO::new);
    }

    //todo
    public Answer fromDT0(AnswerDTO answerDTO, User asker) {
        return null;
    }
}
