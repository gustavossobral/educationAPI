package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaRepository;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.CriarTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.DetalharTurmaDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid CriarTurmaDTO dto){
        var turma = new TurmaEntity(dto);
        repository.save(turma);

        var uri = URI.create("/turma/" + turma.getId());

        return ResponseEntity.created(uri).body("Turma de " + turma.getMateria() + " criada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var turma = repository.getReferenceById(id);

        var response = new DetalharTurmaDTO(turma);

        return ResponseEntity.ok(response);
    }

}
