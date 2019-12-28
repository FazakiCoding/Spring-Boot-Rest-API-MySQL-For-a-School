package com.school.SchoolBackEnd.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CLASSES")
public class Classe {

    private long id;

    private Level level;

    private Major major;

    @NotNull
    @Column(unique = true)
    private String classTitle;

    public Classe(long id, Level level, Major major, @NotNull String classTitle) {
        this.id = id;
        this.level = level;
        this.major = major;
        this.classTitle = classTitle;
    }
    public Classe() {
        this.id = 0;
        this.level = null;
        this.major = null;
        this.classTitle = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "LEVEL_ID")
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    @ManyToOne
    @JoinColumn(name = "MAJOR_ID")
    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }
}
