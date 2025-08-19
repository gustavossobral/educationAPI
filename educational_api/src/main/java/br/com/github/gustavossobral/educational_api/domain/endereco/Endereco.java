package br.com.github.gustavossobral.educational_api.domain.endereco;

import br.com.github.gustavossobral.educational_api.domain.endereco.dto.AtualizarEnderecoDTO;
import br.com.github.gustavossobral.educational_api.domain.endereco.dto.CadastroEnderecoDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
@Embeddable
public class Endereco {

    private int cep;
    private String cidade;
    private String logradouro;
    private String bairro;
    private short numero;
    private String complemento;

    public Endereco(@Valid CadastroEnderecoDTO endereco) {
        this.numero = endereco.numero();
        this.cidade = endereco.cidade();
        this.cep = endereco.cep();
        this.bairro = endereco.bairro();
        this.logradouro = endereco.logradouro();
        this.complemento = endereco.complemento();
    }

    public Endereco() {

    }

    public void atualizar(@Valid AtualizarEnderecoDTO endereco) {

        if (endereco.cidade() != null){
            this.cidade = endereco.cidade();
        }
        if (endereco.cep() != 0){
            this.cep = endereco.cep();
        }
        if (endereco.bairro() != null){
            this.bairro = endereco.bairro();
        }
        if (endereco.logradouro() != null){
            this.logradouro = endereco.logradouro();
        }
        if (endereco.numero() != 0){
            this.numero = endereco.numero();
        }
        if (endereco.complemento() != null){
            this.complemento = endereco.complemento();
        }

    }
}
