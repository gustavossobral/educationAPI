package br.com.github.gustavossobral.educational_api.domain.professor.dto;

import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;

public record ResponseCadastroProfessorDTO(

        Long id,
        String nome,
        Materia materia,
        String email

) {

    public ResponseCadastroProfessorDTO(ProfessorEntity professor){
        this(professor.getId(), professor.getNome(), professor.getMateria(), professor.getEmail());
    }
}
