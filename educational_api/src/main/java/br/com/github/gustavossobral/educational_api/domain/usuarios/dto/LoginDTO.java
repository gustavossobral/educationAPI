package br.com.github.gustavossobral.educational_api.domain.usuarios.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(

        @NotNull
        String login,

        @NotNull
        String senha

) {
}
