package com.honca.Services.Interfaces;

import com.honca.Models.Student;

import java.util.List;

public interface StudentService {
void assignSubject(Long studentId,Long subjectId);
    List<Student> findAllStudents();

    Student findStudentById(Long studentId);

    void updateStudent(Student student);

    void delete(Long studentId);

    void saveStudent(Student student);
}
