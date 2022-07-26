package com.example.test.controller;

import java.net.URI;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.test.domain.Student;
import com.example.test.domain.StudentBuilder;
import com.example.test.repository.CommonRepository;

@RestController
@RequestMapping("/api")
public class Controller {
    private CommonRepository<Student> repository;

    @Autowired
    public Controller(CommonRepository<Student> repository) {
        this.repository = repository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Set<Student>> getStudents() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @RequestMapping(value = "/todo", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<?> postStudent(@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        
        String id = Integer.toString(repository.findAll().size() + 1);
        Student result = repository.save(StudentBuilder.create()
                .withId(id).withName(student.getName()).withOld(student.getOld())
                .withDTB(student.getDTB()).build());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String id) {
        repository.delete(repository.findById(id));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todo")
    public ResponseEntity<Student> deleteStudent(@RequestBody Student student) {
        repository.delete(student);
        return ResponseEntity.noContent().build();
    }
}
