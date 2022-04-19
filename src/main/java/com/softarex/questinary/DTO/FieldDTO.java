package com.softarex.questinary.DTO;

import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FieldDTO {
    private Long id;
    private String label;
    private FieldType type;
    private List<String> options;
    private boolean required;
    private boolean active;

    public FieldDTO(Field field) {
        this.id = field.getId();
        this.label = field.getLabel();
        this.type = field.getType();
        this.options = new ArrayList<>(field.getOptions());
        this.required = field.isRequired();
        this.active = field.isActive();
    }
}
