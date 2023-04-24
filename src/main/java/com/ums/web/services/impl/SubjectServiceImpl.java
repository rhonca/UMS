package com.ums.web.services.impl;

import com.ums.web.dto.SubjectDTO;
import com.ums.web.models.Student;
import com.ums.web.models.Subject;
import com.ums.web.repositories.SubjectRepository;
import com.ums.web.services.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectDTO mapToSubjectDTO(Subject subject) {
        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .build();
    }

    public Subject mapToSubject(SubjectDTO subjectDTO) {
        return Subject.builder()
                .id(subjectDTO.getId())
                .name(subjectDTO.getName())
                .build();
    }

    @Override
    public List<SubjectDTO> findAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(this::mapToSubjectDTO).collect(Collectors.toList());
    }

    @Override
    public SubjectDTO findSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).get();
        return mapToSubjectDTO(subject);
    }
    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) {
        Subject subject = mapToSubject(subjectDTO);
        subjectRepository.save(subject);
    }

}
