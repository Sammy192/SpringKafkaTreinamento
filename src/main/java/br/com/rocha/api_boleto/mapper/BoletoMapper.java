package br.com.rocha.api_boleto.mapper;

import br.com.rocha.api_boleto.dtos.BoletoDTO;
import br.com.rocha.api_boleto.entities.BoletoEntity;

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
