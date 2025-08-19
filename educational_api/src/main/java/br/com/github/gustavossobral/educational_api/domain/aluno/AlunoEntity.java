package br.com.github.gustavossobral.educational_api.domain.aluno;

import br.com.github.gustavossobral.educational_api.domain.aluno.dto.AtualizarAlunoDTO;
import br.com.github.gustavossobral.educational_api.domain.aluno.dto.CadastrarAlunoDTO;
import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity(name = "Aluno")
@Table(name = "alunos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long cpf;
    private String email;
    private String matricula;
    private String telefone;
    @Embedded
    private Endereco endereco;

    private boolean ativo = true;

    public AlunoEntity(@Valid CadastrarAlunoDTO dto) {

        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.matricula = dto.matricula();
        this.telefone = dto.telefone();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizar(@Valid AtualizarAlunoDTO dto) {

        if (dto.nome() != null){
            this.nome = dto.nome();
        }
        if (dto.email() != null){
            this.email = dto.email();
        }
        if (dto.telefone() != null){
            this.telefone = dto.telefone();
        }
        if (dto.endereco() != null){
            this.endereco.atualizar(dto.endereco());
        }

    }
}
