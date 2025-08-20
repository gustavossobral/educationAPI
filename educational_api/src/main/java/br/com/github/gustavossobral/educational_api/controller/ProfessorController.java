package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorRepository;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarProfessorDTO dto){
        var professor = new ProfessorEntity(dto);
        repository.save(professor);

        var uri = URI.create("/professor" + professor.getId());
        var response = new ResponseCadastroProfessorDTO(professor);

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListarProfessoresDTO>> listar(@PageableDefault(size=10, sort={"nome"})Pageable pageable){
        var professores = repository.findByAtivoTrue(pageable);

        return ResponseEntity.ok(professores);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarProfessorDTO dto, @PathVariable Long id){
        var professor = repository.getReferenceById(id);
        professor.atualizar(dto);

        var response = new ResponseCadastroProfessorDTO(professor);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar (@PathVariable Long id){
        var professor = repository.getReferenceById(id);
        professor.setAtivo(false);

        return ResponseEntity.ok("Professor(a) " + professor.getNome() + " desativado(a) com sucesso!");
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity ativar (@PathVariable Long id){
        var professor = repository.getReferenceById(id);
        professor.setAtivo(true);

        return ResponseEntity.ok("Professor(a) " + professor.getNome() + " ativado(a) com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var professor = repository.getReferenceById(id);
        var response = new DetalhamentoProfessorDTO(professor);

        return ResponseEntity.ok(response);
    }

}
