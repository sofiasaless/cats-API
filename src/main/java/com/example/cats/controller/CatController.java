package com.example.cats.controller;

import com.example.cats.dto.CatPostDTO;
import com.example.cats.dto.CatPutDTO;
import com.example.cats.model.Cat;
import com.example.cats.service.CatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("cats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;

    @GetMapping
    public ResponseEntity<Page<Cat>> listAll(Pageable page) {
        return ResponseEntity.ok(catService.listAll(page));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cat> findById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Cat>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(catService.findByName(name));
    }

    public ResponseEntity<Cat> save(@RequestBody @Valid CatPostDTO catPostDTO) {
        return new ResponseEntity<>(catService.save(catPostDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        catService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
