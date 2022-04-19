package com.softarex.questinary.repo;

import com.softarex.questinary.entity.Answer;
import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByAsker(User asker, Pageable pageable);
}
