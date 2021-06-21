package me.stefan923.schoolcatalog.controller;

import me.stefan923.schoolcatalog.domain.Student;
import me.stefan923.schoolcatalog.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @GetMapping("")
    public List<Student> list() {
        return studentService.listAll();
    }

    @GetMapping("/{cnp}")
    public ResponseEntity<Student> getByCnp(@PathVariable String cnp) {
        try {
            Student student = studentService.getByCnp(cnp);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void save(@RequestBody Student student) {
        studentService.save(student);
    }

    @PutMapping("/{cnp}")
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable String cnp) {
        try {
            Student existingStudent = studentService.getByCnp(cnp);
            student.setCnp(existingStudent.getCnp());
            studentService.save(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cnp}")
    public void delete(@PathVariable String cnp) {
        studentService.delete(cnp);
    }

    @Autowired
    private void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

}
