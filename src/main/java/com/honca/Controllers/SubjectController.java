package com.honca.Controllers;

import com.honca.Models.Subject;
import com.honca.Models.Teacher;
import com.honca.Services.Interfaces.SubjectService;
import com.honca.Services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final TeacherService teacherService;

    @Autowired
    public SubjectController(SubjectService subjectService, TeacherService teacherService) {
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        List<Subject> subjects = subjectService.findAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/subjects-list";
    }

    @GetMapping("/subjects/{subjectId}")
    public String subjectDetails(@PathVariable("subjectId") Long subjectId, Model model) {
        Subject subject = subjectService.findSubjectById(subjectId);
        model.addAttribute("subject", subject);
        return "subjects/subjects-details";
    }

    @GetMapping("/subjects/{subjectId}/edit")
    public String editSubjectForm(@PathVariable("subjectId") Long subjectId, Model model) {
        Subject subject = subjectService.findSubjectById(subjectId);
        List<Teacher> teachers = teacherService.findAllTeachers();
        model.addAttribute("subject", subject);
        model.addAttribute("teachers", teachers);
        return "subjects/subjects-edit";
    }

    @PostMapping("/subjects/{subjectId}/edit")
    public String updateSubject(@PathVariable("subjectId") Long subjectId, @ModelAttribute("subject") Subject subject) {
        subject.setId(subjectId);
        subjectService.updateSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/{subjectId}/delete")
    public String deleteSubject(@PathVariable("subjectId") Long subjectId) {
        subjectService.delete(subjectId);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/new")
    public String createStudentForm(Model model) {
        Subject subject = new Subject();
        List<Teacher> teachers = teacherService.findAllTeachers();
        model.addAttribute("subject", subject);
        model.addAttribute("teachers", teachers);
        return "subjects/subjects-create";
    }

    @PostMapping("/subjects/new")
    public String saveSubject(@ModelAttribute("subject") Subject subject) {
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

}
