package com.school.SchoolBackEnd.controller;

import com.school.SchoolBackEnd.model.Group;
import com.school.SchoolBackEnd.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;
@RestController
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public Collection<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    @PostMapping("/groups")
    public Group createGroup(@Valid @RequestBody Group group){
        return groupRepository.save(group);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<?> getGroup(@PathVariable Long id)
    {
        Optional<Group> group=groupRepository.findById(id);
        return group.map(response->ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        return groupRepository.findById(id).map(group -> {
            groupRepository.delete(group);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("group " + id + " not found"));
    }
    @PutMapping("/group/{id}")
    public Group updateGroup(@PathVariable Long id, @Valid @RequestBody Group groupRequest) {
        return groupRepository.findById(id).map(group -> {

            group.setId(groupRequest.getId());
            group.setGroupTitle(groupRequest.getGroupTitle());
            group.set_classe(groupRequest.get_classe());

            return groupRepository.save(group);

        }).orElseThrow(() -> new ResourceNotFoundException("group " + id + " not found"));
    }
}
