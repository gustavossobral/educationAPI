package br.com.github.gustavossobral.educational_api.domain.professor.dto;

import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;

public record DetalhamentoProfessorDTO(

        Long id,
        String nome,
        Long cpf,
        String email,
        String telefone,
        Materia materia,
        Endereco endereco

) {

    public DetalhamentoProfessorDTO(ProfessorEntity professor){
        this(professor.getId(), professor.getNome(), professor.getCpf(), professor.getEmail(), professor.getTelefone(), professor.getMateria(), professor.getEndereco());
    }

}
