package com.softarex.questinary.repo;

import com.softarex.questinary.entity.Field;
import com.softarex.questinary.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    Page<Field> findByAsker(User asker, Pageable pageable);

    List<Field> findByAskerId(Long askerId);

}
