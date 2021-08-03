package br.com.gomes.luis.propostas.clients;

import br.com.gomes.luis.propostas.domain.StatusProposta;

public enum StatusAnalise {

    COM_RESTRICAO {
        @Override
        public StatusProposta toPropostaStatus(){
            return StatusProposta.NAO_ELEGIVEL;
        }
    },
    SEM_RESTRICAO {
        @Override
        public StatusProposta toPropostaStatus() {
            return StatusProposta.ELEGIVEL;
        }
    };

    public abstract StatusProposta toPropostaStatus();
}
