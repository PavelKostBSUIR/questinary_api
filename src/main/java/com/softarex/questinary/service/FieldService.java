package com.softarex.questinary.service;

import com.softarex.questinary.DTO.FieldDTO;
import com.softarex.questinary.DTO.UserDTO;
import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.User;
import com.softarex.questinary.repo.FieldAnswerRepository;
import com.softarex.questinary.repo.FieldRepository;
import com.softarex.questinary.repo.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.persistence.FieldResult;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FieldAnswerRepository fieldAnswerRepository;

    public Field convertFromDTO(FieldDTO fieldDTO) {
        Field field = new Field();
        field.setId(fieldDTO.getId());
        field.setLabel(fieldDTO.getLabel());
        field.setOptions(fieldDTO.getOptions());
        field.setActive(fieldDTO.isActive());
        field.setRequired(fieldDTO.isRequired());
        field.setType(fieldDTO.getType());
        return field;
    }

    @Autowired
    UserRepository userRepository;

    public void updateFromDto(FieldDTO fieldDTO, User asker) {
        Field field = convertFromDTO(fieldDTO);
        field.setAsker(asker);
        //todo delete
        fieldRepository.save(field);
    }

    public void saveFromDto(FieldDTO fieldDTO, User asker) {
        Field field = convertFromDTO(fieldDTO);
        field.setId(null);
        field.setAsker(asker);
        //userRepository.save(asker);//todo delete after registration
        fieldRepository.save(field);
    }

    public Page<FieldDTO> findByUser(Pageable pageable, User user) {
        return fieldRepository.findByAsker(user, pageable).map(FieldDTO::new);
    }

    public FieldDTO findById(Long id) {
        return new com.softarex.questinary.DTO.FieldDTO(fieldRepository.findById(id).get());
    }

    public void removeField(Long id) {

        fieldRepository.deleteById(id);
    }

    public List<FieldDTO> findByAskerId(Long id) {
        return fieldRepository.findByAskerId(id).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> findActiveByAskerId(Long id) {
        return findByAskerId(id).stream().filter(FieldDTO::isActive).collect(Collectors.toList());
    }
}
