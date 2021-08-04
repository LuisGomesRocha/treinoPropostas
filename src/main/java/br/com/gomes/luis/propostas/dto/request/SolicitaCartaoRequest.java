package br.com.gomes.luis.propostas.dto.request;

import javax.validation.constraints.NotBlank;

public class SolicitaCartaoRequest {

    public String idProposta;

    public SolicitaCartaoRequest(@NotBlank String id) {
        this.idProposta = id;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
