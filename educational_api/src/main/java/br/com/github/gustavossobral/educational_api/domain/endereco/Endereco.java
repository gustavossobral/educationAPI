package br.com.github.gustavossobral.educational_api.domain.endereco;

import br.com.github.gustavossobral.educational_api.domain.endereco.dto.CadastroEnderecoDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;

@Embeddable
public class Endereco {

    private int cep;
    private String logradouro;
    private String bairro;
    private short numero;
    private String complemento;

    public Endereco(@Valid CadastroEnderecoDTO endereco) {
        this.numero = endereco.numero();
        this.cep = endereco.cep();
        this.bairro = endereco.bairro();
        this.logradouro = endereco.logradouro();
        this.complemento = endereco.complemento();
    }

    public Endereco() {

    }
}
