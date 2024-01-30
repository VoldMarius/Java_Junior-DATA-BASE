package org.example;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Courses")
public class Course {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Длительность_в_месяцах")
    private int duration;
    @Column(name = "Название")
    private String title;

    public Course() {
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
}