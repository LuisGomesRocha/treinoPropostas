package br.com.gomes.luis.propostas.repository;

import br.com.gomes.luis.propostas.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {

    Cartao findByIdProposta(String idProposta);
}

