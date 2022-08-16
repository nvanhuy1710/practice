package com.example.test.controller;

import com.example.test.domain.Student;
import com.example.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class WebController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/todo")
    public String getIndex(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("studentList", students);

        return "index";
    }

    @GetMapping("/todo/maxDTB")
    public String getMax(Model model) {
        List<Student> students = studentService.getMaxDTB();
        model.addAttribute("maxDTB", students);

        return "max";
    }

    @GetMapping(value = "/todo/add")
    public String Add(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @RequestMapping(value = "/todo/add", method = { RequestMethod.POST, RequestMethod.PUT })
    public String AddStudent(@ModelAttribute Student student, Errors errors) {
        if (errors.hasErrors()) {
            return "sorry";
        }
        Integer id = studentService.getAmount() + 1;
        if(StudentService.checkIdList()) {
            id = StudentService.getId();
            StudentService.idUsed(id);
        }
        student.setId(id);
        student.setName(StudentService.nameFormat(student.getName()));
        studentService.add(student);
        return "redirect:/api/todo";
    }



    @GetMapping("/todo/delete")
    public String deleteStudent(@RequestParam("id") String id) {
        Integer idtmp = Integer.valueOf(id);

        studentService.delete(idtmp);
        return "redirect:/api/todo";
    }

    @GetMapping("/todo/find")
    public String findStudent(Model model) {
        model.addAttribute("student", new Student());
        return "find";
    }

    @PostMapping("/todo/find")
    public String findedStudent(@ModelAttribute Student student, Model model) {
        List<Student> students = studentService
                .findByName(StudentService.nameFormat(student.getName()));
        if(students.isEmpty()) {
            return "sorry";
        }
        model.addAttribute("studentList",students);
        return "index";
    }
}
