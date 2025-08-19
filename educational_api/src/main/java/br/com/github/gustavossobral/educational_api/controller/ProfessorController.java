package br.com.github.gustavossobral.educational_api.controller;

import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorRepository;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.CadastrarProfessorDTO;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.ListarProfessoresDTO;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.ResponseCadastroProfessorDTO;
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

}
