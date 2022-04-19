package com.softarex.questinary.repo;

import com.softarex.questinary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    Optional<User> findById(Long id);
}
