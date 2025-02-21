package br.com.rocha.apiboleto.repositories;

import br.com.rocha.apiboleto.entities.BoletoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoletoRepository extends CrudRepository<BoletoEntity, Long> {

    Optional<BoletoEntity> findByCodigoBarras(String codigoBarras);
}
