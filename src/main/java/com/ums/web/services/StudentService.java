package com.ums.web.services;

import com.ums.web.dto.StudentDTO;
import com.ums.web.models.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAllStudents();
    Student saveStudent(Student student);
    StudentDTO findStudentById(Long id);
    void updateStudent(StudentDTO student);

    void delete(Long studentId);
}
