package com.school.SchoolBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAJORS")
public class Major {

    private Long id;

    @NotNull
    @Column(unique = true)
    private String majorTitle;
    @JsonIgnore
    private List<Classe> classes=new ArrayList<>();

    @OneToMany(mappedBy = "major")
    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Major(Long id, @NotNull String majorTitle) {
        this.id = id;
        this.majorTitle = majorTitle;
    }
    public Major() {
        this.id = null;
        this.majorTitle = null;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMajorTitle() {
        return majorTitle;
    }

    public void setMajorTitle(String majorTitle) {
        this.majorTitle = majorTitle;
    }
}
