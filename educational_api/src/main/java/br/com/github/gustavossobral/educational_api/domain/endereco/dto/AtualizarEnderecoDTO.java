package br.com.github.gustavossobral.educational_api.domain.endereco.dto;

public record AtualizarEnderecoDTO(

        int cep,

        short numero,

        String cidade,

        String bairro,

        String logradouro,

        String complemento
) {
}
