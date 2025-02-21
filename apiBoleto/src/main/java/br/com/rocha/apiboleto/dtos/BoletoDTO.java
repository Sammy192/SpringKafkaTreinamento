package br.com.rocha.apiboleto.dtos;

import br.com.rocha.apiboleto.entities.enums.SituacaoBoletoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoDTO {
    private String codigoBarras;
    private SituacaoBoletoEnum situacaoBoleto;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}