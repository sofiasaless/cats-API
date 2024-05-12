package com.example.cats.controller;

import com.example.cats.domain.Cat;
import com.example.cats.service.CatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cats")
@Log4j2
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

}
