package com.honca.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "subjects")
    Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    Teacher teacher;

    public Subject(Long id, String name) {
        this.name = name;
        this.students = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PreRemove
    private void removeSubjectAssociations() {
        for (Student student : this.students) {
            student.getSubjects().remove(this);
        }
    }
}
