package br.com.github.gustavossobral.educational_api.domain.aluno.dto;

import br.com.github.gustavossobral.educational_api.domain.endereco.dto.AtualizarEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record AtualizarAlunoDTO(

        String nome,

        @Email
        String email,

        String telefone,

        @Valid
        AtualizarEnderecoDTO endereco

) {
}
