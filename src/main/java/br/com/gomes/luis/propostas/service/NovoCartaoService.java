package br.com.gomes.luis.propostas.service;
import br.com.gomes.luis.propostas.clients.CartoesClient;
import br.com.gomes.luis.propostas.domain.Cartao;
import br.com.gomes.luis.propostas.domain.Proposta;
import br.com.gomes.luis.propostas.domain.StatusProposta;
import br.com.gomes.luis.propostas.dto.request.SolicitaCartaoRequest;
import br.com.gomes.luis.propostas.dto.response.SolicitaCartaoResponse;
import br.com.gomes.luis.propostas.repository.CartaoRepository;
import br.com.gomes.luis.propostas.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovoCartaoService {

    @Autowired
    CartoesClient cartoesClient;

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    PropostaRepository propostaRepository;

    public Cartao gravar(SolicitaCartaoRequest solicitaCartaoRequest) {
        SolicitaCartaoResponse solicitaCartaoResponse = null;

        Proposta proposta = propostaRepository.findById(solicitaCartaoRequest.idProposta).get();
        try {
            solicitaCartaoResponse = cartoesClient.solicitaCartao(solicitaCartaoRequest.idProposta).getBody();
           Cartao cartao = solicitaCartaoResponse.toModel();
            cartaoRepository.save(cartao);
            proposta.setNumeroCartao(solicitaCartaoResponse.getId());
            alterarStatusPropostaCartaoGerado(proposta);
        } catch (FeignException e) {
            return null;
        }
        return null;
    }

    public void alterarStatusPropostaCartaoGerado(Proposta proposta) {

        proposta.setStatusProposta(StatusProposta.APROVADO);
        propostaRepository.save(proposta);

    }
}
