 	package com.crud.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.Entity.Models;
import com.crud.demo.service.SystemService;

@RestController
@RequestMapping("/api")
public class ModelController {

	@Autowired
    private SystemService systemService;

    @GetMapping
    public List<Models> getAllModels() {
        return systemService.getAllModels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Models> getModelById(@PathVariable int id) {
        return systemService.getModelById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Models createModels(@RequestBody Models models) {
        return systemService.createModel(models);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Models> updateModels(@PathVariable int id, @RequestBody Models modelDetails) {
        return ResponseEntity.ok(systemService.updateModel(id, modelDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable int id) {
    	systemService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
