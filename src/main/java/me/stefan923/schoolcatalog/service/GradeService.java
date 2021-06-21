package me.stefan923.schoolcatalog.service;

import me.stefan923.schoolcatalog.domain.Grade;
import me.stefan923.schoolcatalog.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class GradeService {

    private GradeRepository gradeRepository;

    public List<Grade> listAll() {
        return gradeRepository.findAll();
    }

    public List<Grade> listByCnp(String cnp) {
        return gradeRepository.findAllByCnpEquals(cnp);
    }

    public Grade getById(int id) {
        return gradeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public double getAverageByCnp(String cnp) {
        return listByCnp(cnp).stream().mapToInt(Grade::getGrade).average().orElseThrow(NoSuchElementException::new);
    }

    public void save(Grade grade) {
        gradeRepository.save(grade);
    }

    public void delete(int id) {
        gradeRepository.deleteById(id);
    }

    @Autowired
    private void setGradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

}
