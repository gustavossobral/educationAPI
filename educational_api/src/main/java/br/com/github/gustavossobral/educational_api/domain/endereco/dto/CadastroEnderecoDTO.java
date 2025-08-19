package br.com.github.gustavossobral.educational_api.domain.endereco.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroEnderecoDTO(

        @NotNull
        int cep,

        @NotNull
        short numero,

        @NotBlank
        String cidade,

        @NotBlank
        String bairro,

        @NotBlank
        String logradouro,

        String complemento
) {

}
