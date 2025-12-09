package br.com.memsageria.api_boleto.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.memsageria.api_boleto.entity.BoletoEntity;

@Repository
public interface BoletoRepository extends CrudRepository<BoletoEntity, Long> {

	 Optional<BoletoEntity> findByCodigoBarras(String codigoBarras);
}
