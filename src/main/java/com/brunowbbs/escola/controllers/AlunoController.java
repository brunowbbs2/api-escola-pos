package com.brunowbbs.escola.controllers;

import com.brunowbbs.escola.dtos.AlunoRequestDTO;
import com.brunowbbs.escola.dtos.AlunoResponseDTO;
import com.brunowbbs.escola.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService alunoService){
        this.service =  alunoService;
    }

    @GetMapping
    public ResponseEntity<Page<AlunoResponseDTO>> listarTodos(Pageable pageable){
        return ResponseEntity.ok(this.service.listarPaginado(pageable));
    }

    @GetMapping("/{id}")
    public AlunoService.AlunoResponseByIdDTO buscarPorId(@PathVariable String id){
        return this.service.buscarPorID(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<AlunoResponseDTO> buscarPorEmail(@RequestParam(defaultValue = "",required = false) String email){
        return ResponseEntity.ok(this.service.buscarAlunoPorEmail(email));
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrar(@Valid @RequestBody AlunoRequestDTO body){
        AlunoResponseDTO dto = this.service.cadastrar(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public AlunoResponseDTO atualizar(@PathVariable String id, @Valid @RequestBody AlunoRequestDTO dto){
        return  this.service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id){
        this.service.excluir(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
// ResponseEntity