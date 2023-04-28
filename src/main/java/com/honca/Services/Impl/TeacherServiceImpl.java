package com.honca.Services.Impl;

import com.honca.Models.Teacher;
import com.honca.Repositories.TeacherRepository;
import com.honca.Services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findById(Long teacherId) {
        return teacherRepository.findById(teacherId).get();
    }

    @Override
    public void deleteById(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
