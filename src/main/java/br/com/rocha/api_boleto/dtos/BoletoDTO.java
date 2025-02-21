package br.com.rocha.api_boleto.dtos;

import br.com.rocha.api_boleto.entities.enums.SituacaoBoletoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoDTO {
    private Long id;
    private String codigoBarras;
    private SituacaoBoletoEnum situacaoBoleto;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}