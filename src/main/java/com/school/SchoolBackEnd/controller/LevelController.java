package com.school.SchoolBackEnd.controller;

import com.school.SchoolBackEnd.model.Level;
import com.school.SchoolBackEnd.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
public class LevelController {
    @Autowired
    private LevelRepository levelRepository;

    @GetMapping("/levels")
    public Collection<Level> getAllLevels(){
        return levelRepository.findAll();
    }

    @PostMapping("/levels")
    public Level createLevel(@Valid @RequestBody Level level){
        return levelRepository.save(level);
    }

    @GetMapping("/level/{id}")
    public ResponseEntity<?> getLevel(@PathVariable Long id)
    {
        Optional<Level> level=levelRepository.findById(id);
        return level.map(response->ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/level/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
        return levelRepository.findById(id).map(level -> {
            levelRepository.delete(level);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("level " + id + " not found"));
    }
    @PutMapping("/level/{id}")
    public Level updateLevel(@PathVariable Long id, @Valid @RequestBody Level levelRequest) {
        return levelRepository.findById(id).map(level -> {

            level.setId(levelRequest.getId());
            level.setLevelTitle(levelRequest.getLevelTitle());
            level.setClasses(levelRequest.getClasses());

            return levelRepository.save(level);

        }).orElseThrow(() -> new ResourceNotFoundException("level " + id + " not found"));
    }
}
