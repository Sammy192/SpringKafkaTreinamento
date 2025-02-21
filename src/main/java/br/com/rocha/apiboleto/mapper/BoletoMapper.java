package br.com.rocha.apiboleto.mapper;

import br.com.rocha.apiboleto.dtos.BoletoDTO;
import br.com.rocha.apiboleto.entities.BoletoEntity;

public class BoletoMapper {

    public static BoletoDTO toDto(BoletoEntity boleto) {
        return BoletoDTO.builder()
                .codigoBarras(boleto.getCodigoBarras())
                .situacaoBoleto(boleto.getSituacaoBoleto())
                .dataCriacao(boleto.getDataCriacao())
                .dataAtualizacao(boleto.getDataAtualizacao())
               .build();
    }
}
