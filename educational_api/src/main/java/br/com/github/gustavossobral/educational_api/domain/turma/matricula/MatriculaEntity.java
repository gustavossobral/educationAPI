package br.com.github.gustavossobral.educational_api.domain.turma.matricula;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="solicitacao_matricula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private TurmaEntity turma;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;
}
