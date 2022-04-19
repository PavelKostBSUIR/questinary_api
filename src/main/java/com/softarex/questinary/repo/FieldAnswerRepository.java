package com.softarex.questinary.repo;

import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.FieldAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldAnswerRepository extends JpaRepository<FieldAnswer, Long> {
}
