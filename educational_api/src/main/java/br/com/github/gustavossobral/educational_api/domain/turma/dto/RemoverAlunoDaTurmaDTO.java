package br.com.github.gustavossobral.educational_api.domain.turma.dto;

import jakarta.validation.constraints.NotNull;

public record RemoverAlunoDaTurmaDTO(

        @NotNull
        Long idAluno,

        @NotNull
        Long idTurma

) {
}
