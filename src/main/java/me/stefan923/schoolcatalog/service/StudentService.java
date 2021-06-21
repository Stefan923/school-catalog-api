package me.stefan923.schoolcatalog.service;

import me.stefan923.schoolcatalog.domain.Student;
import me.stefan923.schoolcatalog.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class StudentService {

    private StudentRepository studentRepository;

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student getByCnp(String cnp) {
        return studentRepository.findById(cnp).orElseThrow(NoSuchElementException::new);
    }

    public void delete(String cnp) {
        studentRepository.deleteById(cnp);
    }

    @Autowired
    private void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

}
