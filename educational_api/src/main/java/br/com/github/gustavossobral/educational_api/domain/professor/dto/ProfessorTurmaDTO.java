package br.com.github.gustavossobral.educational_api.domain.professor.dto;

import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;

public record ProfessorTurmaDTO(
        Long id,
        String nome,
        String email
) {
    public ProfessorTurmaDTO(ProfessorEntity professor) {
        this(
                professor != null ? professor.getId() : null,
                professor != null ? professor.getNome() : null,
                professor != null ? professor.getEmail() : null
        );
    }
}