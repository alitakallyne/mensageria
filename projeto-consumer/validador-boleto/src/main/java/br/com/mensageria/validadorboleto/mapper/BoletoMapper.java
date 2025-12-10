package br.com.mensageria.validadorboleto.mapper;

import br.com.mensageria.avro.Boleto;
import br.com.mensageria.validadorboleto.entity.BoletoEntity;
import br.com.mensageria.validadorboleto.entity.enums.SituacaoBoleto;

public class BoletoMapper {

    public static BoletoEntity toEntity(Boleto boleto) {
        return BoletoEntity.builder()
                .codigoBarras(boleto.getCodigoBarras().toString())
                .situacaoBoleto(SituacaoBoleto.values()[boleto.getSituacaoBoleto()])
                .build();
    }

    public static Boleto toAvro(BoletoEntity boleto) {
        return Boleto.newBuilder()
                .setCodigoBarras(boleto.getCodigoBarras())
                .setSituacaoBoleto(boleto.getSituacaoBoleto().ordinal()).build();
    }
}
