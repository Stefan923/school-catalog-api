package me.stefan923.schoolcatalog.repository;

import me.stefan923.schoolcatalog.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> { }
