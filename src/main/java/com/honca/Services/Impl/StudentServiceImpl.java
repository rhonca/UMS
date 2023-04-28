package com.honca.Services.Impl;

import com.honca.Repositories.StudentRepository;
import com.honca.Repositories.SubjectRepository;
import com.honca.Services.Interfaces.StudentService;
import com.honca.Models.Student;
import com.honca.Models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public void updateStudent(Student student) {
        saveStudent(student);
    }

    @Override
    public void delete(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        studentRepository.delete(student);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void assignSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId).get();
        Subject subject = subjectRepository.findById(subjectId).get();
        student.addSubject(subject);
        studentRepository.save(student);

    }
}
