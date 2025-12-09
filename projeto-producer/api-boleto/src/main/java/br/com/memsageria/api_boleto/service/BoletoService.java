package br.com.memsageria.api_boleto.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.memsageria.api_boleto.controller.exception.ApplicationException;
import br.com.memsageria.api_boleto.dto.BoletoDTO;
import br.com.memsageria.api_boleto.entity.BoletoEntity;
import br.com.memsageria.api_boleto.entity.enums.SituacaoBoleto;
import br.com.memsageria.api_boleto.mapper.BoletoMapper;
import br.com.memsageria.api_boleto.repository.BoletoRepository;
import br.com.memsageria.api_boleto.service.kafka.BoletoProducer;

@Service
public class BoletoService {

	 private  final BoletoDTO BoletoDTO ;
	private final BoletoRepository boletoRepository;
	 private final BoletoProducer boletoProducer;
	 
	 
	 public BoletoService(BoletoRepository boletoRepository, BoletoProducer boletoProducer) {
	        this.BoletoDTO = new BoletoDTO();
			this.boletoRepository = boletoRepository;
			this.boletoProducer = boletoProducer;
	 }
	 
	  public BoletoDTO salvar(String codigoBarras) {
	        var boletoOptional = boletoRepository.findByCodigoBarras(codigoBarras);
	        if (boletoOptional.isPresent()) {
	            throw new ApplicationException("Já existe uma solicitação de pagamento para esse boleto");
	        }

	        var boletoEntity = BoletoEntity.builder()
	                .codigoBarras(codigoBarras)
	                .situacaoBoleto(SituacaoBoleto.INICIALIZADO)
	                .dataCriacao(LocalDateTime.now())
	                .dataAtualizacao(LocalDateTime.now())
	                .build();
	   
	        boletoRepository.save(boletoEntity);
	        boletoProducer.enviarMensagem(BoletoMapper.toAvro(boletoEntity));
	        return BoletoMapper.toDTO(boletoEntity);
	    }
}
