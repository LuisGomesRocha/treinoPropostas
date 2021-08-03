package br.com.gomes.luis.propostas.clients;

import br.com.gomes.luis.propostas.dto.request.AnalisePropostaRequest;
import br.com.gomes.luis.propostas.dto.response.AnalisePropostaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "analise", url = "http://localhost:9999")
public interface AnaliseClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    @ResponseBody
    ResponseEntity<AnalisePropostaResponse> analisaProposta(@RequestBody AnalisePropostaRequest analisePropostaRequest);
}

