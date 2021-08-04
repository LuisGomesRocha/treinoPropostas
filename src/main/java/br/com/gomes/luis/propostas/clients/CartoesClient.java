package br.com.gomes.luis.propostas.clients;

import br.com.gomes.luis.propostas.dto.response.SolicitaCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "cartoes", url = "http://localhost:8888")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET, path = "/api/cartoes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<SolicitaCartaoResponse> solicitaCartao(@RequestParam("idProposta") String idProposta);

}
