package com.ums.web.controllers;

import com.ums.web.dto.StudentDTO;
import com.ums.web.models.Student;
import com.ums.web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<StudentDTO> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "students-list";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "students-create";
    }

    @PostMapping("/students/new")
    public String saveClub(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/edit")
    public String editStudentForm(@PathVariable("studentId") Long studentId, Model model) {
        StudentDTO student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);
        return "students-edit";
    }

    @PostMapping("/students/{studentId}/edit")
    public String updateStudent(@PathVariable("studentId") Long studentId, @ModelAttribute("student") StudentDTO student) {
        student.setId(studentId);
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.delete(studentId);
        return "redirect:/students";
    }
}
