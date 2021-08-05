package br.com.gomes.luis.propostas.repository;

import br.com.gomes.luis.propostas.domain.Bloqueio;
import br.com.gomes.luis.propostas.domain.StatusBloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, String> {

    Optional<Bloqueio> findByIdCartaoAndStatusIn(String idCartao, List<Enum> status);

    List<Bloqueio> findAllByStatus(Enum<StatusBloqueio> status);
}
