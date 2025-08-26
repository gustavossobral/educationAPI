package br.com.github.gustavossobral.educational_api.domain.aluno.dto;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;

public record AlunoSimpleDTO(
        Long id,
        String nome,
        String matricula
) {
    public AlunoSimpleDTO(AlunoEntity aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getMatricula());
    }
}

