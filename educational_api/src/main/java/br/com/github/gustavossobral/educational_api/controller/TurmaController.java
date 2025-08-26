package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoRepository;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaRepository;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.CriarTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.DetalharTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.MatriculaEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.MatriculaRepository;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.dto.ListarSolicitacoesMatriculasDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.dto.SolicitarMatriculaDTO;
import jakarta.persistence.EntityNotFoundException;
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
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid CriarTurmaDTO dto){
        var turma = new TurmaEntity(dto);
        turmaRepository.save(turma);

        var uri = URI.create("/turma/" + turma.getId());

        return ResponseEntity.created(uri).body("Turma de " + turma.getMateria() + " criada com sucesso!");
    }

    @PostMapping("/matricula")
    @Transactional
    public ResponseEntity solicitarMatricula(@RequestBody @Valid SolicitarMatriculaDTO dto){
        var aluno = alunoRepository.findById(dto.idAluno()).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
        var turma = turmaRepository.findById(dto.idTurma()).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada!"));
        var matricula = new MatriculaEntity();

        matricula.setTurma(turma);
        matricula.setAluno(aluno);

        matriculaRepository.save(matricula);

        return ResponseEntity.ok("solicitação feita com sucesso!");
    }

    @GetMapping("/matriculas/listar")
    public ResponseEntity listarSolicitacoesMatriculas(){
        var matriculas = matriculaRepository.findAll();

        matriculas.forEach(m -> {
            m.setAluno(alunoRepository.getReferenceById(m.getAluno().getId()));
            m.setTurma(turmaRepository.getReferenceById(m.getTurma().getId()));
        });

        var response = matriculas.stream().map(ListarSolicitacoesMatriculasDTO::new).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var turma = turmaRepository.getReferenceById(id);

        var response = new DetalharTurmaDTO(turma);

        return ResponseEntity.ok(response);
    }

}
