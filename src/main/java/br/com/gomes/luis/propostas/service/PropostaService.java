package br.com.gomes.luis.propostas.service;

import br.com.gomes.luis.propostas.domain.Proposta;
import br.com.gomes.luis.propostas.repository.PropostaRepository;
import br.com.gomes.luis.propostas.utils.CpfCnpjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropostaService {

    @Autowired
    PropostaRepository propostaRepository;

    public Proposta salvarProposta(Proposta proposta) throws Exception {

        proposta.setDocumento(CpfCnpjUtils.formaterDocumento(proposta.getDocumento()));
        return propostaRepository.save(proposta);

    }

}
