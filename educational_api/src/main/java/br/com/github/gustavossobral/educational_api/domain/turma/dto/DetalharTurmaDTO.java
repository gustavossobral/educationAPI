package br.com.github.gustavossobral.educational_api.domain.turma.dto;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;

import java.util.Set;

public record DetalharTurmaDTO(

        Long id,
        Materia materia,
        ProfessorEntity professor,
        Set<AlunoEntity> alunos

) {

    public DetalharTurmaDTO(TurmaEntity turma){
        this(turma.getId(), turma.getMateria(),turma.getProfessor(), turma.getAlunos());
    }

}
