package com.example.cats.controller;

import com.example.cats.dto.CatPostDTO;
import com.example.cats.dto.CatPutDTO;
import com.example.cats.model.Cat;
import com.example.cats.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
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

    @Operation(summary = "Lista todos gatos em página",
            description = "O padrão da página é 20, use o parametro para alterar o número de gatos exibidos por página"
//            tags = {"cats"}
    )
    @GetMapping
    public ResponseEntity<Page<Cat>> listAll(@ParameterObject Pageable page) {
        return ResponseEntity.ok(catService.listAll(page));
    }

    @Operation(summary = "Retorna um JSON gato de acordo com o ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Cat> findById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findByIdOrThrowBadRequestException(id));
    }

    @Operation(summary = "Retorna os gatos de acordo com o nome fornecido")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Cat>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(catService.findByName(name));
    }

    @Operation(summary = "Salvando novas entidades 'Gato'")
    @PostMapping(path = "/save")
    public ResponseEntity<Cat> save(@RequestBody @Valid CatPostDTO catPostDTO) {
        return new ResponseEntity<>(catService.save(catPostDTO), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Quando o gato não existe no banco de dados")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        catService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Atualizando entidades 'Gato' de acordo com Id")
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid CatPutDTO cat){
        catService.replace(cat);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/home")
    public String handleWelcome() {
        return "home";
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Não autorizado"),
    })
    @GetMapping(path = "/admin/home")
    public String handleAdminHome() {
        return "home_admin";
    }

    @GetMapping(path = "/user/home")
    public String handleUserHome() {
        return "home_user";
    }

}
