package com.example.cats.controller;

import com.example.cats.domain.Cat;
import com.example.cats.service.CatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("cats")
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;

    @GetMapping
    public ResponseEntity<List<Cat>> listAll() {
        return ResponseEntity.ok(catService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cat> findById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findById(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Cat>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(catService.findByName(name));
    }

    @PostMapping()
    public ResponseEntity<Cat> save(@RequestBody @Valid Cat cat) {
        return new ResponseEntity<>(catService.save(cat), HttpStatus.CREATED);
    }
 
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        catService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Cat cat){
        catService.replace(cat);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
