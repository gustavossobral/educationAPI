package br.com.github.gustavossobral.educational_api.domain.professor;

import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.AtualizarProfessorDTO;
import br.com.github.gustavossobral.educational_api.domain.professor.dto.CadastrarProfessorDTO;
import br.com.github.gustavossobral.educational_api.domain.turma.TurmaEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity(name = "Professor")
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long cpf;
    private String email;
    private Materia materia;
    private String telefone;
    @Embedded
    private Endereco endereco;

    private boolean ativo = true;

    @OneToOne(mappedBy = "professor")
    private TurmaEntity turma;

    public ProfessorEntity(@Valid CadastrarProfessorDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.materia = dto.materia();
        this.telefone = dto.telefone();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizar(@Valid AtualizarProfessorDTO dto) {

        if(dto.nome() != null) {
            this.nome = dto.nome();
        }

        if(dto.email() != null) {
            this.email = dto.email();
        }

        if(dto.telefone() != null) {
            this.telefone = dto.telefone();
        }

        if(dto.endereco() != null) {
            this.endereco.atualizar(dto.endereco());
        }

    }
}
