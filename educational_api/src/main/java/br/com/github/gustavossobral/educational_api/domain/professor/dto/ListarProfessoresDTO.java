package br.com.github.gustavossobral.educational_api.domain.professor.dto;

import br.com.github.gustavossobral.educational_api.domain.professor.Materia;

public record ListarProfessoresDTO(

        Long id,
        String nome,
        Materia materia,
        String email

) {
}
