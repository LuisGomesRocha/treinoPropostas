package br.com.gomes.luis.propostas.controller;

import br.com.gomes.luis.propostas.domain.Proposta;
import br.com.gomes.luis.propostas.dto.request.PropostaRequest;
import br.com.gomes.luis.propostas.dto.response.PropostaResponse;
import br.com.gomes.luis.propostas.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponse> novaProposta(@Validated @RequestBody PropostaRequest propostaRequest ) throws Exception {
        Proposta proposta = propostaRequest.toModel();
        propostaService.salvarProposta(proposta);
        PropostaResponse propostaResponse = new PropostaResponse(proposta);
        return  ResponseEntity.status(HttpStatus.CREATED).body(propostaResponse);

    }

    @GetMapping
    public ResponseEntity<Proposta> buscaProposta(@Validated @RequestParam String id ) throws Exception {
        Proposta proposta = propostaService.buscaProposta(id);
        return ResponseEntity.status(HttpStatus.OK).body(proposta);

    }






}
