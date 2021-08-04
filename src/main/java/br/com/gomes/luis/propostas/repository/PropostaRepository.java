package br.com.gomes.luis.propostas.repository;

import br.com.gomes.luis.propostas.domain.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, String> {

    List<Proposta> findAllByStatusProposta(Enum status);

    List<Proposta> findAllByStatusPropostaIn(List<Enum> status);


}
