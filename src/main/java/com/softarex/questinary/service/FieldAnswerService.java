package com.softarex.questinary.service;

import com.softarex.questinary.DTO.FieldAnswerDTO;
import com.softarex.questinary.entity.Answer;
import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.FieldAnswer;
import com.softarex.questinary.exception.NotFoundException;
import com.softarex.questinary.repo.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class FieldAnswerService {
    @Autowired
    FieldRepository fieldRepository;

    //todo
    public FieldAnswer fromDTO(FieldAnswerDTO fieldAnswerDTO, Answer answer) {
        Optional<Field> field = fieldRepository.findById(fieldAnswerDTO.getFieldId());
        if (field.isEmpty()) {
            throw new NotFoundException();
        }
        return new FieldAnswer(null, answer, field.get(), fieldAnswerDTO.getOptions());
    }
}
