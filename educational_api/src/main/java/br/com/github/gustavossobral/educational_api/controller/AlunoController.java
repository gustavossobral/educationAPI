package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoRepository;
import br.com.github.gustavossobral.educational_api.domain.aluno.dto.CadastrarAlunoDTO;
import br.com.github.gustavossobral.educational_api.domain.aluno.dto.ListarAlunosDTO;
import br.com.github.gustavossobral.educational_api.domain.aluno.dto.ResponseCadastroAlunoDTO;
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

}
