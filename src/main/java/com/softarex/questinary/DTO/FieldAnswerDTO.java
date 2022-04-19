package com.softarex.questinary.DTO;

import com.softarex.questinary.entity.FieldAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FieldAnswerDTO {

    private Long fieldId;
    private String fieldLabel;
    private List<String> options;

    public FieldAnswerDTO(FieldAnswer fieldAnswer) {
        this.fieldId = fieldAnswer.getField().getId();
        this.fieldLabel = fieldAnswer.getField().getLabel();
        this.options = new ArrayList<>(fieldAnswer.getOptions());
    }


}
