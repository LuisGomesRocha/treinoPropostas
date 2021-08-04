package br.com.gomes.luis.propostas.repository;

import br.com.gomes.luis.propostas.domain.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, String> {

}
