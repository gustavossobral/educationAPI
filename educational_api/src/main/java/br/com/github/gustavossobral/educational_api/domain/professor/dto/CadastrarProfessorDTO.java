package br.com.github.gustavossobral.educational_api.domain.professor.dto;

import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import br.com.github.gustavossobral.educational_api.domain.endereco.dto.CadastroEnderecoDTO;
import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarProfessorDTO(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotNull
        Long cpf,

        @Enumerated(EnumType.STRING)
        Materia materia,

        @NotBlank
        String telefone,

        @Valid
        CadastroEnderecoDTO endereco

) {
}
