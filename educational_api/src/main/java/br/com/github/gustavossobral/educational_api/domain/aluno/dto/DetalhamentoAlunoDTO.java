package br.com.github.gustavossobral.educational_api.domain.aluno.dto;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;

public record DetalhamentoAlunoDTO(

        Long id,
        String nome,
        Long cpf,
        String matricula,
        String email,
        String telefone,
        Endereco endereco

) {
    public DetalhamentoAlunoDTO(AlunoEntity aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getMatricula(), aluno.getEmail(), aluno.getTelefone(), aluno.getEndereco());
    }
}
