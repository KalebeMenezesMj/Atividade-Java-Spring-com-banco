package br.edu.fatecpg.jpaspring.repository;

import br.edu.fatecpg.jpaspring.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByCep(String cep);
}
