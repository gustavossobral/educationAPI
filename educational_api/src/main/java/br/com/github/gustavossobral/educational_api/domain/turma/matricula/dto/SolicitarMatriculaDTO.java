package br.com.github.gustavossobral.educational_api.domain.turma.matricula.dto;

import jakarta.validation.constraints.NotNull;

public record SolicitarMatriculaDTO(

        @NotNull
        Long idAluno,

        @NotNull
        Long idTurma

) {

}
