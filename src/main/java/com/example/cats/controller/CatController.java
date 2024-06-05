package com.example.cats.controller;

import com.example.cats.dto.CatPostDTO;
import com.example.cats.dto.CatPutDTO;
import com.example.cats.model.Cat;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Cat>> listAll() {
        return ResponseEntity.ok(catService.listAll());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Cat> findById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findByIdOrThrowBadRequestException(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Cat>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(catService.findByName(name));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/save")
    public ResponseEntity<Cat> save(@RequestBody @Valid CatPostDTO catPostDTO) {
        return new ResponseEntity<>(catService.save(catPostDTO), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        catService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid CatPutDTO cat){
        catService.replace(cat);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/home")
    public String handleWelcome() {
        return "home";
    }

    @GetMapping(path = "/admin/home")
    public String handleAdminHome() {
        return "home_admin";
    }

    @GetMapping(path = "/user/home")
    public String handleUserHome() {
        return "home_user";
    }

}
