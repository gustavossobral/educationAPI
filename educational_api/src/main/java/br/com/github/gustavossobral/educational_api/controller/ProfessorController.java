package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorRepository;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.*;
import br.com.github.gustavossobral.educational_api.domain.usuarios.Usuario;
import br.com.github.gustavossobral.educational_api.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarProfessorDTO dto){
        var professor = new ProfessorEntity(dto);
        professorRepository.save(professor);

        var senhaCriptografada = passwordEncoder.encode(dto.telefone());
        var user = new Usuario(
                dto.email(),
                senhaCriptografada,
                "PROFESSOR");
        usuarioRepository.save(user);

        var uri = URI.create("/professor" + professor.getId());
        var response = new ResponseCadastroProfessorDTO(professor);

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ESTUDANTE','PROFESSOR','ADMIN')")
    public ResponseEntity<Page<ListarProfessoresDTO>> listar(@PageableDefault(size=10, sort={"nome"})Pageable pageable){
        var professores = professorRepository.findByAtivoTrue(pageable);

        return ResponseEntity.ok(professores);
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarProfessorDTO dto, @PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        professor.atualizar(dto);

        var response = new ResponseCadastroProfessorDTO(professor);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity desativar (@PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        professor.setAtivo(false);

        return ResponseEntity.ok("Professor(a) " + professor.getNome() + " desativado(a) com sucesso!");
    }

    @PatchMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity ativar (@PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        professor.setAtivo(true);

        return ResponseEntity.ok("Professor(a) " + professor.getNome() + " ativado(a) com sucesso!");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ESTUDANTE','PROFESSOR','ADMIN')")
    public ResponseEntity detalhar(@PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        var response = new DetalhamentoProfessorDTO(professor);

        return ResponseEntity.ok(response);
    }

}
