package br.com.github.gustavossobral.educational_api.domain.turma;

import br.com.github.gustavossobral.educational_api.domain.aluno.AlunoEntity;
import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import br.com.github.gustavossobral.educational_api.domain.professor.ProfessorEntity;
import br.com.github.gustavossobral.educational_api.domain.turma.dto.CriarTurmaDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Materia materia;

    @OneToOne
    @JoinColumn(name = "professor_id", unique = true)
    private ProfessorEntity professor;

    @ManyToMany
    @JoinTable(
            name = "turma_alunos",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private Set<AlunoEntity> alunos = new HashSet<>();

    public TurmaEntity(@Valid CriarTurmaDTO dto) {
        this.materia = dto.materia();
    }
}
