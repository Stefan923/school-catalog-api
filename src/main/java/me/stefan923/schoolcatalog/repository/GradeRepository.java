package me.stefan923.schoolcatalog.repository;

import me.stefan923.schoolcatalog.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findAllByCnpEquals(String cnp);

}
