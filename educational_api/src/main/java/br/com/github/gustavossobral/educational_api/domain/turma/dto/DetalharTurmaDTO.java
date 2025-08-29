package br.com.github.gustavossobral.educational_api.domain.turma.dto;

import br.com.github.gustavossobral.educational_api.domain.aluno.dto.AlunoSimpleDTO;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.ProfessorTurmaDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;

import java.util.Set;
import java.util.stream.Collectors;

public record DetalharTurmaDTO(

        Long id,
        String materia,
        ProfessorTurmaDTO professor,
        Set<AlunoSimpleDTO> alunos

) {
    public DetalharTurmaDTO(TurmaEntity turma) {
        this(
                turma.getId(),
                turma.getMateria().toString(),
                new ProfessorTurmaDTO(turma.getProfessor()),
                turma.getAlunos().stream()
                        .map(AlunoSimpleDTO::new)
                        .collect(Collectors.toSet())
        );
    }
}

