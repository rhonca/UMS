package com.honca.Services.Interfaces;

import com.honca.Models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> findAllTeachers();

    void save(Teacher teacher);

    Teacher findById(Long teacherId);

    void deleteById(Long teacherId);
}
