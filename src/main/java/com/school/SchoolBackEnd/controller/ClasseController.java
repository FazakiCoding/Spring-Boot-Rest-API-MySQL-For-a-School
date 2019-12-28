package com.school.SchoolBackEnd.controller;

import com.school.SchoolBackEnd.model.Classe;
import com.school.SchoolBackEnd.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
public class ClasseController {

    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping("/classes")
    public Collection<Classe> getAllClasses(){
        return classeRepository.findAll();
    }

    @PostMapping("/classes")
    public Classe createClasse(@Valid @RequestBody Classe classe){
        return classeRepository.save(classe);
    }

    @GetMapping("/classe/{id}")
    public ResponseEntity<?> getClasse(@PathVariable Long id)
    {
        Optional<Classe> classe=classeRepository.findById(id);
        return classe.map(response->ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/classe/{id}")
        public ResponseEntity<?> deleteClasse(@PathVariable Long id) {
        return classeRepository.findById(id).map(classe -> {
            classeRepository.delete(classe);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("classe " + id + " not found"));
    }
    @PutMapping("/classe/{id}")
    public Classe updateClasse(@PathVariable Long id, @Valid @RequestBody Classe classeRequest) {
        return classeRepository.findById(id).map(classe -> {

            classe.setId(classeRequest.getId());
            classe.setClassTitle(classeRequest.getClassTitle());
            classe.setLevel(classeRequest.getLevel());
            classe.setMajor(classeRequest.getMajor());

            return classeRepository.save(classe);
        }).orElseThrow(() -> new ResourceNotFoundException("classe " + id + " not found"));
    }
}
