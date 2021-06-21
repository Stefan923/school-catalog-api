package me.stefan923.schoolcatalog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.stefan923.schoolcatalog.converter.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "students")
@AllArgsConstructor
@RequiredArgsConstructor
public class Student {

    @Id
    @Column(length = 13, nullable = false)
    private String cnp;

    @Column(length = 32, nullable = false)
    private String firstName;

    @Column(length = 64, nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateOfBirth;

    @Column(length = 13)
    private String phoneNumber;

}

