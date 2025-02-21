package br.com.rocha.api_boleto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletoRequestDTO {
    private String codigoBarras;
}
