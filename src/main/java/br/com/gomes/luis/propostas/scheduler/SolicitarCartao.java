package br.com.gomes.luis.propostas.scheduler;

import br.com.gomes.luis.propostas.domain.Proposta;
import br.com.gomes.luis.propostas.domain.StatusProposta;
import br.com.gomes.luis.propostas.dto.request.SolicitaCartaoRequest;
import br.com.gomes.luis.propostas.repository.PropostaRepository;
import br.com.gomes.luis.propostas.service.NovoCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static br.com.gomes.luis.propostas.domain.StatusProposta.ELEGIVEL;

@Configuration
@EnableAsync
@EnableScheduling
public class SolicitarCartao {

    @Autowired
    PropostaRepository propostaRepository;//1

    @Autowired
    NovoCartaoService novoCartaoService;//1

    @Scheduled(fixedRate = 15000)
    void guardarCartoes() {
        List<Proposta> propostaList = buscarPropostasAptas();//1
        if (!propostaList.isEmpty()) {//1
            for (Proposta proposta : propostaList) {//1
                SolicitaCartaoRequest solicitaCartaoRequest = proposta.toSolicitaCartao();//1
                novoCartaoService.gravar(solicitaCartaoRequest);
            }
        }

    }

    private List<Proposta> buscarPropostasAptas() {
        List<Proposta> propostasAptas = propostaRepository.findAllByStatusProposta(ELEGIVEL);
        return propostasAptas;
    }
}
