package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoRepository;
import br.com.github.gustavossobral.educational_api.domain.aluno.dto.*;
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
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarAlunoDTO dto){
        var aluno = new AlunoEntity(dto);
        repository.save(aluno);

        var uri = URI.create("/aluno" + aluno.getId());
        var response = new ResponseCadastroAlunoDTO(aluno);

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListarAlunosDTO>> listar(@PageableDefault(size = 10, sort ={"nome"})Pageable pageable){
        var alunos = repository.findByAtivoTrue(pageable).map(ListarAlunosDTO::new);

        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarAlunoDTO dto, @PathVariable Long id){
        var aluno = repository.getReferenceById(id);
        aluno.atualizar(dto);

        var response = new ResponseCadastroAlunoDTO(aluno);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var aluno = repository.getReferenceById(id);
        aluno.setAtivo(false);

        return ResponseEntity.ok("Aluno(a) " + aluno.getNome() + " desativado(a) com sucesso!");
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity ativar(@PathVariable Long id){
        var aluno = repository.getReferenceById(id);
        aluno.setAtivo(true);

        return ResponseEntity.ok("Aluno(a) " + aluno.getNome() + " ativado(a) com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var aluno = repository.getReferenceById(id);
        var response = new DetalhamentoAlunoDTO(aluno);

        return ResponseEntity.ok(response);
    }
}
