package br.com.rocha.validador_boleto.mapper;

import br.com.rocha.avro.Boleto;
import br.com.rocha.validador_boleto.entities.BoletoEntity;
import br.com.rocha.validador_boleto.entities.enums.SituacaoBoletoEnum;

public class BoletoMapper {

    private BoletoMapper() {}

    public static BoletoEntity toEntity(Boleto boleto) {
        return BoletoEntity.builder()
                .codigoBarras(boleto.getCodigoBarras().toString())
                .situacaoBoleto(SituacaoBoletoEnum.values()[boleto.getSituacaoBoleto()])
                .build();
    }

    public static Boleto toAvro(BoletoEntity boleto) {
        return Boleto.newBuilder()
                .setCodigoBarras(boleto.getCodigoBarras())
                .setSituacaoBoleto(boleto.getSituacaoBoleto().ordinal()).build();
    }
}
