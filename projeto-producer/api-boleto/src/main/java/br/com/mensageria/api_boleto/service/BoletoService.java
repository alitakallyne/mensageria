package br.com.mensageria.api_boleto.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.alita.avro.Boleto;
import br.com.mensageria.api_boleto.controller.exception.ApplicationException;
import br.com.mensageria.api_boleto.controller.exception.NotFoundException;
import br.com.mensageria.api_boleto.dto.BoletoDTO;
import br.com.mensageria.api_boleto.entity.BoletoEntity;
import br.com.mensageria.api_boleto.entity.enums.SituacaoBoleto;
import br.com.mensageria.api_boleto.mapper.BoletoMapper;
import br.com.mensageria.api_boleto.repository.BoletoRepository;
import br.com.mensageria.api_boleto.service.kafka.BoletoProducer;

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

		
		public BoletoDTO buscarBoletoPorCodigoBarras (String codigoBarras) {
	        return BoletoMapper.toDTO(recuperarBoleto(codigoBarras));
		}

		private BoletoEntity recuperarBoleto (String codigoBarras) {
	        return boletoRepository.findByCodigoBarras(codigoBarras)
	                .orElseThrow(() -> new NotFoundException("Boleto não encontrado"));
	  }

      public void atualizar(BoletoEntity entity) {
       var boletoAtual = recuperarBoleto(entity.getCodigoBarras());
	   
	   boletoAtual.setSituacaoBoleto(entity.getSituacaoBoleto());
	   boletoAtual.setDataAtualizacao(LocalDateTime.now());
	   boletoRepository.save(boletoAtual);
      }
}
