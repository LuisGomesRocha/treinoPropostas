package br.com.gomes.luis.propostas.service;

import br.com.gomes.luis.propostas.domain.Biometria;
import br.com.gomes.luis.propostas.domain.Cartao;
import br.com.gomes.luis.propostas.repository.BiometriaRepository;
import br.com.gomes.luis.propostas.repository.CartaoRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class NovaBiometriaService {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    BiometriaRepository biometriaRepository;


    public boolean verificaCartaoExistente(String idCartao) {
        return cartaoRepository.existsById(idCartao);
    }

    public Biometria salvarBiometria(String biometria, String idCartao) {

        Cartao cartao = cartaoRepository.findById(idCartao).get();
        Biometria novaBiometria = new Biometria(biometria, cartao);
        biometriaRepository.save(novaBiometria);
        return novaBiometria;
    }

    public String converterImagemParaString(MultipartFile imagem) {
        String imagemEmString = null;
        try {
            imagemEmString = Base64.encodeBase64String(imagem.getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return imagemEmString;
    }
}
