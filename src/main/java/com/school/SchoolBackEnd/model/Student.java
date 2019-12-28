package com.school.SchoolBackEnd.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String famillyName;

    private Group group;

    private Classe classe;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfJoining;

    private String Address;

    private String country;

    private String phone;

    private String email;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    @ManyToOne
    @JoinColumn(name = "CLASS_ID")
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Student() {
        this.id = null;
        this.firstName = null;
        this.famillyName = null;
        this.group = null;
        this.classe = null;
        this.dateOfBirth = null;
        this.dateOfJoining = null;
        Address = null;
        this.country = null;
        this.phone = null;
        this.email = null;

    }

    public Student(Long id, String firstName, String famillyName, Group group, Classe classe, Date dateOfBirth, Date dateOfJoining, String address, String country, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.famillyName = famillyName;
        this.group = group;
        this.classe = classe;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        Address = address;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamillyName() {
        return famillyName;
    }

    public void setFamillyName(String famillyName) {
        this.famillyName = famillyName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
