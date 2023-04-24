package com.ums.web.services.interfaces;

import com.ums.web.dto.SubjectDTO;
import com.ums.web.models.Subject;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> findAllSubjects();

    SubjectDTO findSubjectById(Long subjectId);

    public Subject saveSubject(com.ums.web.models.Subject subject);

    void updateSubject(SubjectDTO subject);
}
