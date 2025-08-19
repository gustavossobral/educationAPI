package br.com.github.gustavossobral.educational_api.domain.aluno.dto;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;

public record ResponseCadastroAlunoDTO(

        Long id,
        String nome,
        String matricula,
        String email

) {

    public ResponseCadastroAlunoDTO(AlunoEntity entity) {
        this(entity.getId(), entity.getNome(), entity.getMatricula(), entity.getEmail());
    }

}
