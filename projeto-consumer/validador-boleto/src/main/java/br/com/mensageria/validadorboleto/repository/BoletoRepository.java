package br.com.mensageria.validadorboleto.repository;

import br.com.mensageria.validadorboleto.entity.BoletoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends CrudRepository<BoletoEntity, Long> {
}
