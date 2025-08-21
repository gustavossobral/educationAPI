package br.com.github.gustavossobral.educational_api.domain.turma.dto;

import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;
import jakarta.validation.constraints.NotNull;

public record CriarTurmaDTO(

        @NotNull(message = "A matéria é obrigatória")
        Materia materia,

        ProfessorEntity professor

) {
}
