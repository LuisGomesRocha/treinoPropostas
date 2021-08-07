package br.com.gomes.luis.propostas.dto.request;

import javax.validation.constraints.NotBlank;

public class BloqueioRequest {
    public String sistemaResponsavel;

    public BloqueioRequest(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
