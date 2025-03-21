package dk.kea.studentdtostart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private LocalDate bornDate;

    private LocalTime bornTime;

    public Student(String name, String password, LocalDate bornDate, LocalTime bornTime) {
        this.name = name;
        this.password = password;
        this.bornDate = bornDate;
        this.bornTime = bornTime;
    }

    public Student(Long id, String name, String password, LocalDate bornDate, LocalTime bornTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.bornDate = bornDate;
        this.bornTime = bornTime;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public LocalTime getBornTime() {
        return bornTime;
    }

    public void setBornTime(LocalTime bornTime) {
        this.bornTime = bornTime;
    }
}

