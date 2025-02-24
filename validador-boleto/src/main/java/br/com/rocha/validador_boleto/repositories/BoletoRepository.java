package br.com.rocha.validador_boleto.repositories;

import br.com.rocha.validador_boleto.entities.BoletoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends CrudRepository<BoletoEntity, Long> {
}
