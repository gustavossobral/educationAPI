package br.com.github.gustavossobral.educational_api.domain.turma.dto;

import jakarta.validation.constraints.NotNull;

public record AdicionarProfessorNaTurmaDTO(

        @NotNull
        Long idProfessor,

        @NotNull
        Long idTurma

) {
}
