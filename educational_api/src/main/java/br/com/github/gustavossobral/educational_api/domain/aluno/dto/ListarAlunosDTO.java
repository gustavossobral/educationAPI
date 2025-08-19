package br.com.github.gustavossobral.educational_api.domain.aluno.dto;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import org.springframework.data.domain.Pageable;

public record ListarAlunosDTO(

        Long id,
        String nome,
        String email,
        String matricula

) {

    public ListarAlunosDTO(AlunoEntity aluno){
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula()
        );
    }
}
