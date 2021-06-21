package me.stefan923.schoolcatalog.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    private String cnp;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;

    public Student() { }

    public Student(String cnp, String firstName, String lastName, String phoneNumber, int age) {
        this.cnp = cnp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    @Id
    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getAge() == student.getAge() && getCnp().equals(student.getCnp())
                && Objects.equals(getFirstName(), student.getFirstName())
                && Objects.equals(getLastName(), student.getLastName())
                && Objects.equals(getPhoneNumber(), student.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCnp(), getFirstName(), getLastName(), getPhoneNumber(), getAge());
    }

    @Override
    public String toString() {
        return "Student{" +
                "cnp='" + cnp + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                '}';
    }

}

