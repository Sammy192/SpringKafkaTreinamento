package br.com.rocha.api_boleto.repositories;

import br.com.rocha.api_boleto.entities.BoletoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoletoRepository extends CrudRepository<BoletoEntity, Long> {

    Optional<BoletoEntity> findByCodigoBarras(String codigoBarras);
}
