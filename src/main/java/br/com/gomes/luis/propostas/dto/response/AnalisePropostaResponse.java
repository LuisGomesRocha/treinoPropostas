package br.com.gomes.luis.propostas.dto.response;

import br.com.gomes.luis.propostas.clients.StatusAnalise;

public class AnalisePropostaResponse {

    private StatusAnalise statusAnalise;

    public StatusAnalise getStatusAnalise() {
        return statusAnalise;
    }

    public void setStatusAnalise(StatusAnalise statusAnalise) {
        this.statusAnalise = statusAnalise;
    }
}
