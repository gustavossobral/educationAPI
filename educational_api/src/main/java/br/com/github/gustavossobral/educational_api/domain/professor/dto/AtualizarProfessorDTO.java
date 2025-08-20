package br.com.github.gustavossobral.educational_api.domain.professor.dto;

import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import br.com.github.gustavossobral.educational_api.domain.endereco.dto.AtualizarEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record AtualizarProfessorDTO(

        String nome,
        String telefone,

        @Email
        String email,

        AtualizarEnderecoDTO endereco

) {
}
