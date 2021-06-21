package me.stefan923.schoolcatalog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.stefan923.schoolcatalog.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "grades")
@AllArgsConstructor
@RequiredArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 13, nullable = false)
    private String cnp;

    @Column(length = 32, nullable = false)
    private String subject;

    @Column(nullable = false)
    private int grade;

    @Column(name = "creation_time", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime creationTime;

}
