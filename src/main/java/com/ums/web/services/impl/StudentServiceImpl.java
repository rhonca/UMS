package com.ums.web.services.impl;

import com.ums.web.dto.StudentDTO;
import com.ums.web.models.Student;
import com.ums.web.models.Subject;
import com.ums.web.repositories.StudentRepository;
import com.ums.web.repositories.SubjectRepository;
import com.ums.web.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public StudentDTO mapToStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .albumId(student.getAlbumId())
                .subjects(student.getSubjects())
                .build();
    }

    public Student mapToStudent(StudentDTO student) {
        return Student.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .albumId(student.getAlbumId())
                .subjects(student.getSubjects())
                .build();
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToStudentDTO).collect(Collectors.toList());
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentDTO findStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        return mapToStudentDTO(student);
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        Student student = mapToStudent(studentDTO);
        studentRepository.save(student);
    }

    @Override
    public void delete(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        studentRepository.deleteById(studentId);
    }


    @Override
    public void assignSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId).get();
        Subject subject = subjectRepository.findById(subjectId).get();
        Set<Subject> subjects = student.getSubjects();
        subjects.add(subject);
        student.setSubjects(subjects);
        studentRepository.save(student);
    }
}
