package com.example.test.controller;

import com.example.test.domain.Student;
import com.example.test.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/api")
public class WebController {
    private final CommonRepository<Student> repository;

    @Autowired
    public WebController(CommonRepository<Student> repository) {
        this.repository = repository;
    }

    // @GetMapping("/todo")
    // public ResponseEntity<Set<Student>> getStudents() {
    //     return ResponseEntity.ok(repository.findAll());
    // }
    @GetMapping("/todo")
    public String getIndex(Model model) {
        Set<Student> students = repository.findAll();
        model.addAttribute("studentList", students);

        return "index";
    }

    @GetMapping("/todo/maxDTB")
    // public ResponseEntity<Set<Student>> getMaxDTB() {
    //     return ResponseEntity.ok(repository.maxDTB());
    // }
    public String getMax(Model model) {
        Set<Student> students = repository.maxDTB();
        model.addAttribute("maxDTB", students);

        return "max";
    }

    @GetMapping("/todo/{id}")
    // public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
    //     return ResponseEntity.ok(repository.findById(id));
    // }
    public String getStudent(@PathVariable Integer id, Model model) {
        Set<Student> student = new HashSet<>();
        if(repository.findById(id) == null) {
            return "sorry";
        }
        student.add(repository.findById(id));
        model.addAttribute("studentList", student);
        return "index";
    }

    // @GetMapping("/todo/{dtb}")
    // public ResponseEntity<Set<Student>> getAllStudentBydtb(@PathVariable Float dtb) {
    //     return ResponseEntity.ok(repository.searchByDTB(dtb));
    // }
    @RequestMapping(value = "/todo/add")
    public String Add(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @RequestMapping(value = "/todo/added", method = { RequestMethod.POST, RequestMethod.PUT })
    // public ResponseEntity<?> postStudent(@Valid @RequestBody Student student, Errors errors) {
    //     if (errors.hasErrors()) {
    //         return ResponseEntity.badRequest().body(errors);
    //     }
        
    //     //String id = Integer.toString(repository.findAll().size() + 1);
    //     Integer id = repository.getLast() + 1;
    //     Student result = repository.save(StudentBuilder.create()
    //             .withId(id).withName(student.getName()).withOld(student.getOld())
    //             .withDTB(student.getDTB()).build());
    //     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    //             .buildAndExpand(result.getId()).toUri();
    //     ResponseEntity.created(location);
    //     //return ResponseEntity.created(location).build();
    //     return ResponseEntity.ok(repository.findAll());
    // }
    public String AddStudent(@ModelAttribute Student student, Errors errors) {
        if (errors.hasErrors()) {
            return "sorry";
        }
        Integer id = repository.getLast() + 1;
        student.setId(id);
        Student result = repository.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
               .buildAndExpand(result.getId()).toUri();
        ResponseEntity.created(location);
        return "success";
    }

    @GetMapping("/todo/delete")
    public String deleteStudent(@RequestParam String id, Model model) {
        Integer idtmp = Integer.valueOf(id);
        repository.delete(repository.findById(idtmp));
        getIndex(model);
        return "success";
    }
}
