package com.ums.web.controllers;

import com.ums.web.dto.StudentDTO;
import com.ums.web.dto.SubjectDTO;
import com.ums.web.models.Student;
import com.ums.web.models.Subject;
import com.ums.web.repositories.SubjectRepository;
import com.ums.web.services.interfaces.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        List<SubjectDTO> subjects = subjectService.findAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/subjects-list";
    }

    @GetMapping("/subjects/new")
    public String createStudentForm(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subjects/subjects-create";
    }

    @PostMapping("/subjects/new")
    public String saveSubject(@ModelAttribute("subject") Subject subject) {
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }
    @GetMapping("/subjects/{subjectId}/edit")
    public String editSubjectForm(@PathVariable("subjectId") Long subjectId, Model model) {
        SubjectDTO subject = subjectService.findSubjectById(subjectId);
        model.addAttribute("subject", subject);
        return "subjects/subjects-edit";
    }
    @PostMapping("/subjects/{subjectId}/edit")
    public String updateSubject(@PathVariable("subjectId") Long subjectId, @ModelAttribute("subject")SubjectDTO subject) {
        subject.setId(subjectId);
        subjectService.updateSubject(subject);
        return "redirect:/subjects";
    }

}
