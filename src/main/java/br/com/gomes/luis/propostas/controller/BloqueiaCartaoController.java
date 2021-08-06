package br.com.gomes.luis.propostas.controller;

import br.com.gomes.luis.propostas.domain.Bloqueio;
import br.com.gomes.luis.propostas.domain.Cartao;
import br.com.gomes.luis.propostas.domain.StatusBloqueio;
import br.com.gomes.luis.propostas.dto.response.BloqueioResponse;
import br.com.gomes.luis.propostas.service.BloqueioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/bloquear_cartao")
public class BloqueiaCartaoController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BloqueioService bloqueioService;//1

    @Autowired
    HttpServletRequest request;

    @PostMapping
    @Transactional
    public ResponseEntity<?> bloquearCartao(@RequestParam(name = "idCartao") String idCartao, UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, idCartao);
        if (cartao == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado.");
        }
        String ipClienteSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        if (bloqueioService.isBloqueioAtivo(idCartao)){

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já se encontra bloqueado.");
        }

        Bloqueio bloqueioCartao = new Bloqueio(idCartao, ipClienteSolicitante, userAgent, StatusBloqueio.SOLICITADO);

        bloqueioService.criar(bloqueioCartao);

        BloqueioResponse bloqueioResponse = new BloqueioResponse(bloqueioCartao.getId());
        return ResponseEntity.created(uriComponentsBuilder.path("/consultaBloqueio/{idBloqueio}").buildAndExpand(bloqueioResponse.getId()).toUri()).body(bloqueioResponse);
    }
}

