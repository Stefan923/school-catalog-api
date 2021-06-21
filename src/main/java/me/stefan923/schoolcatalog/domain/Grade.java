package me.stefan923.schoolcatalog.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grade {

    private int id;
    private String cnp;
    private String subject;
    private int grade;

    public Grade() { }

    public Grade(int id, String cnp, String subject, int grade) {
        this.id = id;
        this.cnp = cnp;
        this.subject = subject;
        this.grade = grade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade1 = (Grade) o;
        return getGrade() == grade1.getGrade() && getCnp().equals(grade1.getCnp())
                && Objects.equals(getSubject(), grade1.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCnp(), getSubject(), getGrade());
    }

    @Override
    public String toString() {
        return "Grade{" +
                "cnp='" + cnp + '\'' +
                ", subject='" + subject + '\'' +
                ", grade=" + grade +
                '}';
    }

}
