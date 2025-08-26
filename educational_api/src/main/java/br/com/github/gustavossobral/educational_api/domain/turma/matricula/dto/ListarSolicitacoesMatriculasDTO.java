package br.com.github.gustavossobral.educational_api.domain.turma.matricula.dto;

import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import br.com.github.gustavossobral.educational_api.domain.turma.matricula.MatriculaEntity;

public record ListarSolicitacoesMatriculasDTO(

        Long idSolicitacao,
        String nomeAluno,
        String matriculaAluno,
        Materia materiaTurma,
        Long idTurma

) {

    public ListarSolicitacoesMatriculasDTO(MatriculaEntity matricula) {
        this(
                matricula.getId(),
                matricula.getAluno().getNome(),
                matricula.getAluno().getMatricula(),
                matricula.getTurma().getMateria(),
                matricula.getTurma().getId()
        );

    }
}
