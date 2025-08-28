package br.com.github.gustavossobral.educational_api.domain.aluno.dto;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;

import java.util.Set;

public record DetalhamentoAlunoDTO(

        Long id,
        String nome,
        Long cpf,
        String matricula,
        String email,
        String telefone,
        Endereco endereco,
        Set<TurmaEntity> turmas

) {
    public DetalhamentoAlunoDTO(AlunoEntity aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getMatricula(), aluno.getEmail(), aluno.getTelefone(), aluno.getEndereco(), aluno.getTurmas());
    }
}
