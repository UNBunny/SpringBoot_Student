package com.student.controller;


import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Inject
    private StudentProperties studentProperties;

    @Inject
    private StudentService studentService;


    @GetMapping("/msg")
    public String getMessage(@RequestHeader("user-agent") String userAgent) {
        return userAgent;
    }

    @GetMapping
    public Collection<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentService.get(id);
    }

    @GetMapping(path = "/single", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Student> getSingleStudent(@RequestParam("id") Optional<Long> optional) {
        return ResponseEntity.ok(studentService.get(optional.orElse(1L)));
    }

    @GetMapping("/search/{department}")
    public Collection<Student> getStudentsPerDepartment(@PathVariable String department, @RequestParam("name") Optional<String> optional) {
        return studentService.getAllStudentsInDepartment(department, optional.orElse(""));
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentService.add(student);
        if (student.getId() > 0) {
            URI uri = URI.create("/college/student/" + student.getId());
            System.out.println(uri);
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
