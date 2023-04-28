package com.honca.Services.Interfaces;

import com.honca.Models.Subject;

import java.util.List;

public interface SubjectService{
    List<Subject> findAllSubjects();

    Subject findSubjectById(Long subjectId);

    void updateSubject(Subject subject);

    void saveSubject(Subject subject);

    void delete(Long subjectId);
}
