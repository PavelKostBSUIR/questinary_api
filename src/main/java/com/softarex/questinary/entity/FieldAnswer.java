package com.softarex.questinary.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "field_answer")
public class FieldAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @ElementCollection
    @CollectionTable(name = "field_answer_options", joinColumns = @JoinColumn(name = "field_answer_id"))
    @Column(name = "option")
    private List<String> options;

    public FieldAnswer(Long id, Answer answer, Field field, List<String> options) {
        this.id = id;
        this.answer = answer;
        this.field = field;
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FieldAnswer that = (FieldAnswer) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
