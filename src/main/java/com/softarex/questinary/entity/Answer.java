package com.softarex.questinary.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asker_id")
    private User asker;

    @OneToMany
    @JoinColumn(name = "answer_id")
    private List<FieldAnswer> fieldAnswers = new ArrayList<>();

    public Answer(User asker, List<FieldAnswer> fieldAnswers) {
        this.asker = asker;
        this.fieldAnswers = fieldAnswers;
    }

    //todo what is it?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Answer answer = (Answer) o;
        return id != null && Objects.equals(id, answer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
