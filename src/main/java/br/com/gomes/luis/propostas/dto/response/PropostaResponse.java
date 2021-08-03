package br.com.gomes.luis.propostas.dto.response;

import br.com.gomes.luis.propostas.domain.Proposta;

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
