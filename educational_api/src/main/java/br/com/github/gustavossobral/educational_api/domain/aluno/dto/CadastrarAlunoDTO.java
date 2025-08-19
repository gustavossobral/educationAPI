package br.com.github.gustavossobral.educational_api.domain.aluno.dto;

import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import br.com.github.gustavossobral.educational_api.domain.endereco.dto.CadastroEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarAlunoDTO(

        @NotBlank
        String nome,

        @NotNull
        Long cpf,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String matricula,

        @NotBlank
        String telefone,

        @Valid
        CadastroEnderecoDTO endereco

) {
}
