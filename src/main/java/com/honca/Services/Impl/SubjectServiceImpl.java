package com.honca.Services.Impl;

import com.honca.Models.Subject;
import com.honca.Repositories.SubjectRepository;
import com.honca.Services.Interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).get();
    }

    @Override
    public void updateSubject(Subject subject) {
        saveSubject(subject);
    }

    @Override
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void delete(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).get();
        subjectRepository.delete(subject);
    }
}
