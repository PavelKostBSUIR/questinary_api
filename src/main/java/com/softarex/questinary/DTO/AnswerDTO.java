package com.softarex.questinary.DTO;

import com.softarex.questinary.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AnswerDTO {
    private List<FieldAnswerDTO> fieldAnswers;

    public AnswerDTO(Answer answer) {
        this.fieldAnswers = answer.getFieldAnswers().stream().map(FieldAnswerDTO::new).collect(Collectors.toList());
    }
}
