package com.school.SchoolBackEnd.controller;

import com.school.SchoolBackEnd.model.Major;
import com.school.SchoolBackEnd.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
public class MajorController {
    @Autowired
    private MajorRepository majorRepository;

    @GetMapping("/majors")
    public Collection<Major> getAllClasses(){
        return majorRepository.findAll();
    }

    @PostMapping("/majors")
    public Major createMajor(@Valid @RequestBody Major major){
        return majorRepository.save(major);
    }

    @GetMapping("/major/{id}")
    public ResponseEntity<?> getMajor(@PathVariable Long id)
    {
        Optional<Major> major=majorRepository.findById(id);
        return major.map(response->ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/major/{id}")
    public ResponseEntity<?> deleteMajor(@PathVariable Long id) {
        return majorRepository.findById(id).map(major -> {
            majorRepository.delete(major);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("major " + id + " not found"));
    }
    @PutMapping("/major/{id}")
    public Major updateMajor(@PathVariable Long id, @Valid @RequestBody Major MajorRequest) {
        return majorRepository.findById(id).map(major -> {

            major.setId(MajorRequest.getId());
            major.setMajorTitle(MajorRequest.getMajorTitle());
            major.setClasses(MajorRequest.getClasses());

            return majorRepository.save(major);
        }).orElseThrow(() -> new ResourceNotFoundException("major " + id + " not found"));
    }
}
