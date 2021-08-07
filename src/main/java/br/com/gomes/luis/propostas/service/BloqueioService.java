package br.com.gomes.luis.propostas.service;

import br.com.gomes.luis.propostas.clients.CartoesClient;
import br.com.gomes.luis.propostas.domain.Bloqueio;
import br.com.gomes.luis.propostas.domain.StatusBloqueio;
import br.com.gomes.luis.propostas.dto.request.BloqueioRequest;
import br.com.gomes.luis.propostas.repository.BloqueioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.gomes.luis.propostas.domain.StatusBloqueio.BLOQUEADO;
import static br.com.gomes.luis.propostas.domain.StatusBloqueio.SOLICITADO;

@Service
public class BloqueioService {

    @Autowired
    BloqueioRepository bloqueioRepository;

    @Autowired
    CartoesClient cartoesClient;

    private String sistemaResponsavel = "proposta";

    public boolean isBloqueioAtivo(String idCartao) {
        List<Enum> status = new ArrayList<>();
        status.add(BLOQUEADO);
        status.add(SOLICITADO);
        Optional<Bloqueio> bloqueio = bloqueioRepository.findByIdCartaoAndStatusIn(idCartao, status);
        if (bloqueio.isPresent()) {
            return true;
        }
        return false;
    }

    public void criar(Bloqueio bloqueioCartao) {
        bloqueioRepository.save(bloqueioCartao);
    }

    public void bloquearCartao(Bloqueio bloqueio) {

        ResponseEntity responseEntity = cartoesClient.bloquearCartao(bloqueio.getIdCartao(), new BloqueioRequest(sistemaResponsavel));

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            bloqueioRepository.save(bloqueio);

        }else {

            return;
        }

    }

    public List<Bloqueio> buscarSolicitacoesDeBloqueio() {
        List<Bloqueio> cartoesASeremBloqueados = bloqueioRepository.findAllByStatus(SOLICITADO);
        return cartoesASeremBloqueados;
    }
}
