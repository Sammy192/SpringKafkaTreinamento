package br.com.rocha.apiboleto.mapper;

import br.com.rocha.apiboleto.dtos.BoletoDTO;
import br.com.rocha.apiboleto.entities.BoletoEntity;
import br.com.rocha.apiboleto.entities.enums.SituacaoBoletoEnum;
import br.com.rocha.avro.Boleto;

public class BoletoMapper {

    public static BoletoDTO toDto(BoletoEntity boleto) {
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
                .situacaoBoleto(SituacaoBoletoEnum.values()[boleto.getSituacaoBoleto()])
                .build();
    }
}
