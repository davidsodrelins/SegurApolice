package br.com.seguradora.api.repository;

import br.com.seguradora.api.model.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {

    Optional<Apolice> findByNumero(Long numero);
}
