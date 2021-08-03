package br.com.gomes.luis.propostas.dto.request;

import javax.validation.constraints.NotBlank;

public class AnalisePropostaRequest {
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;


    public AnalisePropostaRequest(@NotBlank String documento, @NotBlank String nome, @NotBlank String id) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

}
