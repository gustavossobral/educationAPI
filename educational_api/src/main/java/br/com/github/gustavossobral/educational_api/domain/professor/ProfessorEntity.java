package br.com.github.gustavossobral.educational_api.domain.professor;

import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Professor")
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

}
