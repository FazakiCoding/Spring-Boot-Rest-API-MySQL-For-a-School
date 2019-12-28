package com.school.SchoolBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "LEVELS")
public class Level {
    private Long id;

    @NotNull
    @Column(unique = true)
    private String levelTitle;
    @JsonIgnore
    private List<Classe> classes=new ArrayList<>();

    @OneToMany(mappedBy = "major")
    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public Level(Long id, @NotNull String levelTitle) {
        this.id = id;
        this.levelTitle = levelTitle;
    }
    public Level() {
        this.id = null;
        this.levelTitle = null;
    }
}
