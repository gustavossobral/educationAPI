package br.com.github.gustavossobral.educational_api.domain.coordenador;

import br.com.github.gustavossobral.educational_api.domain.endereco.Endereco;
import br.com.github.gustavossobral.educational_api.domain.professor.Materia;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Coordenador")
@Table(name = "coordenadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class CoordenadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long cpf;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;

    private boolean ativo = true;

}
