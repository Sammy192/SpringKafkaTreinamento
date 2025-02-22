package br.com.rocha.apiboleto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoletoRequestDTO {

    @NotNull(message = "Codigo de barras é obrigatório")
    @NotBlank(message = "Codigo de barras não pode estar vazio")
    private String codigoBarras;
}