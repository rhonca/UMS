package com.honca.Controllers;

import com.honca.Models.Student;
import com.honca.Models.Subject;
import com.honca.Services.Interfaces.StudentService;
import com.honca.Services.Interfaces.SubjectService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "students/students-list";
    }

    @GetMapping("/students/{studentId}")
    public String studentDetails(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);
        return "students/students-details";
    }

    @GetMapping("/students/{studentId}/edit")
    public String editStudentForm(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);
        return "students/students-edit";
    }

    @PostMapping("/students/{studentId}/edit")
    public String updateStudent(@PathVariable("studentId") Long studentId, @ModelAttribute("student") Student student) {
        student.setId(studentId);
        studentService.updateStudent(student);
        return "redirect:/students/{studentId}";
    }

    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.delete(studentId);
        return "redirect:/students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "students/students-create";
    }

    @PostMapping("/students/new")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/subjects")
    public String addSubjectForm(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        List<Subject> subjects = subjectService.findAllSubjects();
        model.addAttribute("student", student);
        model.addAttribute("subjects", subjects);
        return "students/students-subjects";
    }

    @PostMapping("/students/{studentId}/subjects")
    public String assignSubject(@PathVariable("studentId") Long studentId, HttpServletRequest request) {
        studentService.assignSubject(studentId, Long.valueOf(request.getParameter("subjectId")));
        return "redirect:/students";
    }
}
