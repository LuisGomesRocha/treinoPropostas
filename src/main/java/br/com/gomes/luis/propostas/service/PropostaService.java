package br.com.gomes.luis.propostas.service;

import br.com.gomes.luis.propostas.clients.AnaliseClient;
import br.com.gomes.luis.propostas.domain.Proposta;
import br.com.gomes.luis.propostas.domain.StatusProposta;
import br.com.gomes.luis.propostas.dto.response.AnalisePropostaResponse;
import br.com.gomes.luis.propostas.repository.PropostaRepository;
import br.com.gomes.luis.propostas.service.exception.DadosImprocessaveisException;
import br.com.gomes.luis.propostas.utils.CpfCnpjUtils;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class PropostaService {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    AnaliseClient analiseClient;

    public Proposta salvarProposta(Proposta proposta) throws Exception {
        proposta.setDocumento(CpfCnpjUtils.formaterDocumento(proposta.getDocumento()));
        propostaRepository.save(proposta);
        analisaProposta(proposta);
        return proposta;
    }


    public void analisaProposta(Proposta proposta) {
        AnalisePropostaResponse analisePropostaResponse = null;

        try{
            analisePropostaResponse = analiseClient.analisaProposta(proposta.toAnalise()).getBody();
            proposta.setStatus(StatusProposta.ELEGIVEL);
        } catch (FeignException exception){
            if (exception.status() == 422) {
                proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
            }
        }
        propostaRepository.save(proposta);
    }


}
