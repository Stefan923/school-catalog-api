package me.stefan923.schoolcatalog.controller;

import me.stefan923.schoolcatalog.domain.Grade;
import me.stefan923.schoolcatalog.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private GradeService gradeService;

    @GetMapping("")
    public List<Grade> list() {
        return gradeService.listAll();
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Grade> getById(@PathVariable int id) {
        try {
            Grade grade = gradeService.getById(id);
            return new ResponseEntity<>(grade, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cnp={cnp}")
    public ResponseEntity<List<Grade>> listByCnp(@PathVariable String cnp) {
        try {
            List<Grade> grades = gradeService.listByCnp(cnp);
            return new ResponseEntity<>(grades, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cnp={cnp}/average")
    public ResponseEntity<Double> getAverageByCnp(@PathVariable String cnp) {
        try {
            double average = gradeService.getAverageByCnp(cnp);
            return new ResponseEntity<>(average, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void save(@RequestBody Grade grade) {
        gradeService.save(grade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Grade grade, @PathVariable int id) {
        try {
            Grade existingGrade = gradeService.getById(id);
            grade.setId(existingGrade.getId());
            gradeService.save(grade);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        gradeService.delete(id);
    }

    @Autowired
    private void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

}
