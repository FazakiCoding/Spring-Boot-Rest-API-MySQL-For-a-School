package com.school.SchoolBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String groupTitle;

    @JsonIgnore
    private Classe classe;

    public Group(Long id, @NotNull String groupTitle, Classe classe) {
        this.id = id;
        this.groupTitle = groupTitle;
        this.classe = classe;
    }
    public Group() {
        this.id = null;
        this.groupTitle = null;
        this.classe = null;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
    @ManyToOne
    @JoinColumn(name = "classe_id")
    public Classe get_classe() {
        return this.classe;
    }

    public void set_classe(Classe classe) {
        this.classe = classe;
    }
}
