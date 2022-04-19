package com.softarex.questinary.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String label;
    private boolean required;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private FieldType type;

    @ElementCollection
    @CollectionTable(name = "field_options", joinColumns = @JoinColumn(name = "field_id"))
    @Column(name = "option")
    private List<String> options;

    @ManyToOne
    @JoinColumn(name = "asker_id")
    private User asker;

    @OneToMany(mappedBy = "field", cascade = CascadeType.REMOVE)
    private Set<FieldAnswer> fieldAnswers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Field field = (Field) o;
        return id != null && Objects.equals(id, field.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
