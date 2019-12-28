package com.school.SchoolBackEnd.controller;

import com.school.SchoolBackEnd.model.Student;
import com.school.SchoolBackEnd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id)
    {
        Optional<Student> student=studentRepository.findById(id);
        return student.map(response->ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("student " + id + " not found"));
    }
    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentRequest) {
        return studentRepository.findById(id).map(student -> {

            student.setId(studentRequest.getId());
            student.setAddress(studentRequest.getAddress());
            student.setCountry(studentRequest.getCountry());
            student.setDateOfBirth(studentRequest.getDateOfBirth());
            student.setDateOfJoining(studentRequest.getDateOfJoining());
            student.setEmail(studentRequest.getEmail());
            student.setFamillyName(studentRequest.getFamillyName());
            student.setFirstName(studentRequest.getFirstName());
            student.setGroup(studentRequest.getGroup());
            student.setPhone(studentRequest.getPhone());
            student.setClasse(studentRequest.getClasse());


            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("student " + id + " not found"));
    }
}
