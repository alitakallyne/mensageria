package br.com.memsageria.api_boleto.mapper;

import br.com.alita.avro.Boleto;
import br.com.memsageria.api_boleto.dto.BoletoDTO;
import br.com.memsageria.api_boleto.entity.BoletoEntity;

public class BoletoMapper {

	public static BoletoDTO toDTO(BoletoEntity boleto) {
        return BoletoDTO.builder()
                .codigoBarras(boleto.getCodigoBarras())
                .situacaoBoleto(boleto.getSituacaoBoleto())
                .dataCriacao(boleto.getDataCriacao())
                .dataAtualizacao(boleto.getDataAtualizacao())
                .build();
    }
	
	public static Boleto toAvro(BoletoEntity boleto) {
        return Boleto.newBuilder()
                .setCodigoBarras(boleto.getCodigoBarras())
                .setSituacaoBoleto(boleto.getSituacaoBoleto().ordinal())
                .build();
    }
}
