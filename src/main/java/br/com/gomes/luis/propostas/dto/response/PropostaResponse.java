package br.com.gomes.luis.propostas.dto.response;

import br.com.gomes.luis.propostas.domain.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaResponse {

    private String email;
    private String nome;
    private String endereco;

    public PropostaResponse(String email, String nome, String endereco) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
    }

    public PropostaResponse(Proposta proposta) {
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

}
