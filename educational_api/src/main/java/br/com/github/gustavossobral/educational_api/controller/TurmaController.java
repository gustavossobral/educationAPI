package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoRepository;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorRepository;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaRepository;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.AdicionarProfessorNaTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.CriarTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.DetalharTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.RemoverAlunoDaTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.MatriculaEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.MatriculaRepository;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.dto.ListarSolicitacoesMatriculasDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.dto.SolicitarMatriculaDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity criar(@RequestBody @Valid CriarTurmaDTO dto){
        var turma = new TurmaEntity(dto);
        turmaRepository.save(turma);

        var uri = URI.create("/turma/" + turma.getId());

        return ResponseEntity.created(uri).body("Turma de " + turma.getMateria() + " criada com sucesso!");
    }

    @PostMapping("/matricula")
    @Transactional
    @PreAuthorize("hasAnyRole('ESTUDANTE','ADMIN')")
    public ResponseEntity solicitarMatricula(@RequestBody @Valid SolicitarMatriculaDTO dto){
        var aluno = alunoRepository.findById(dto.idAluno()).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
        var turma = turmaRepository.findById(dto.idTurma()).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada!"));
        var matricula = new MatriculaEntity();

        matricula.setTurma(turma);
        matricula.setAluno(aluno);

        matriculaRepository.save(matricula);

        return ResponseEntity.ok("solicitação feita com sucesso!");
    }

    @GetMapping("/matricula/listar")
    @PreAuthorize("hasAnyRole('PROFESSOR','ADMIN')")
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
    @PreAuthorize("hasAnyRole('ESTUDANTE','PROFESSOR','ADMIN')")
    public ResponseEntity detalhar(@PathVariable Long id){
        var turma = turmaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada."));

        var response = new DetalharTurmaDTO(turma);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/matricula/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> aceitarSolicitacaoMatricula(@PathVariable Long id) {
        var solicitacao = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitação não existente."));

        var turma = turmaRepository.findById(solicitacao.getTurma().getId())
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
        var aluno = alunoRepository.findById(solicitacao.getAluno().getId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        turma.getAlunos().add(aluno);
        aluno.getTurmas().add(turma);

        turmaRepository.save(turma);
        alunoRepository.save(aluno);

        matriculaRepository.delete(solicitacao);

        return ResponseEntity.ok("Solicitação aceita com sucesso!");
    }

    @DeleteMapping("/remover-aluno")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity removerAlunoDaTurma(@RequestBody @Valid RemoverAlunoDaTurmaDTO dto){

        var aluno = alunoRepository.findById(dto.idAluno()).orElseThrow(()-> new EntityNotFoundException("Aluno não encontrado."));
        var turma = turmaRepository.findById(dto.idTurma()).orElseThrow(()-> new EntityNotFoundException("Turma não encontrada."));

        turma.getAlunos().remove(aluno);
        aluno.getTurmas().remove(turma);

        turmaRepository.save(turma);
        alunoRepository.save(aluno);

        return ResponseEntity.ok("Aluno " + aluno.getNome() + " removido da turma " + turma.getId() + " com sucesso!");
    }

    @PutMapping("/adicionar-professor")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity adicionarProfessorNaTurma(@RequestBody @Valid AdicionarProfessorNaTurmaDTO dto){
        var professor = professorRepository.findById(dto.idProfessor()).orElseThrow(()-> new EntityNotFoundException("Professor não encontrado."));
        var turma = turmaRepository.findById(dto.idTurma()).orElseThrow(()-> new EntityNotFoundException("Turma não encontrada."));

        turma.setProfessor(professor);
        professor.setTurma(turma);

        professorRepository.save(professor);
        turmaRepository.save(turma);

        return ResponseEntity.ok("Professor(a) " + professor.getNome() + " adicionado(a) a turma " + turma.getId() + " com sucesso!");
    }


    @DeleteMapping("/remover-professor/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity removerProfessorNaTurma(@PathVariable Long id){
        var turma = turmaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Turma não encontrada."));
        var professor = professorRepository.findById(turma.getProfessor().getId()).orElseThrow(()-> new EntityNotFoundException("Professor não encontrado."));

        turma.setProfessor(null);
        professor.setTurma(null);

        professorRepository.save(professor);
        turmaRepository.save(turma);

        return ResponseEntity.ok("Professor(a) da turma " + turma.getId() + " foi removido com sucesso!");
    }

    @DeleteMapping("/excluir-turma/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity excluirTurma(@PathVariable Long id){
        var turma = turmaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada."));
        turmaRepository.delete(turma);

        return ResponseEntity.ok("Turma " + turma.getId() + " excluida com sucesso!");
    }

    //listar turmas

}
