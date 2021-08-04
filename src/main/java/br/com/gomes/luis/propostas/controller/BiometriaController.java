package br.com.gomes.luis.propostas.controller;

import br.com.gomes.luis.propostas.domain.Biometria;
import br.com.gomes.luis.propostas.dto.response.BiometriaResponse;
import br.com.gomes.luis.propostas.service.NovaBiometriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/nova_biometria")
public class BiometriaController {

    @Autowired
    NovaBiometriaService novaBiometriaService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaBiometria(@RequestParam("biometria") MultipartFile biometria, UriComponentsBuilder uriComponentsBuilder, @RequestParam("idCartao") String idCartao) {

        if (biometria.isEmpty()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!novaBiometriaService.verificaCartaoExistente(idCartao)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String biometriaEmString = novaBiometriaService.converterImagemParaString(biometria);

        Biometria objetoBiometria = novaBiometriaService.salvarBiometria(biometriaEmString, idCartao);

        BiometriaResponse novaBiometriaResponse = new BiometriaResponse(objetoBiometria.getId(), objetoBiometria.getBiometria());
        return ResponseEntity.created(uriComponentsBuilder.path("/consultaBiometria/{idBiometria}").buildAndExpand(novaBiometriaResponse.getId()).toUri()).body(novaBiometriaResponse);
    }

}
