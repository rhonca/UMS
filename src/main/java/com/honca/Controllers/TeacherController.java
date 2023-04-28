package com.honca.Controllers;

import com.honca.Models.Teacher;
import com.honca.Services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers/teachers-list";
    }

    @GetMapping("/teachers/{teacherId}")
    public String teacherDetails(@PathVariable("teacherId") Long teacherId, Model model) {
        Teacher teacher = teacherService.findById(teacherId);
        model.addAttribute("teacher", teacher);
        return "teachers/teachers-details";
    }

    @GetMapping("/teachers/{teacherId}/edit")
    public String teacherEdit(@PathVariable("teacherId") Long teacherId, Model model) {
        Teacher teacher = teacherService.findById(teacherId);
        model.addAttribute("teacher", teacher);
        return "teachers/teachers-edit";
    }

    @GetMapping("/teachers/{teacherId}/delete")
    public String teacherDelete(@PathVariable("teacherId") Long teacherId) {
        teacherService.deleteById(teacherId);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/new")
    public String createTeacherForm(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teachers/teachers-create";
    }

    @PostMapping("/teachers/new")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

}
