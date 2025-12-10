package br.com.mensageria.api_boleto.mapper;

import br.com.alita.avro.Boleto;
import br.com.mensageria.api_boleto.dto.BoletoDTO;
import br.com.mensageria.api_boleto.entity.BoletoEntity;
import br.com.mensageria.api_boleto.entity.enums.SituacaoBoleto;

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


    public static BoletoEntity toEntity(Boleto boleto) {
        return BoletoEntity.builder()
                .codigoBarras(boleto.getCodigoBarras().toString())
                .situacaoBoleto(SituacaoBoleto.values()[boleto.getSituacaoBoleto()])
                .build();
    }
}
