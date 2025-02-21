package br.com.rocha.api_boleto.controllers;

import br.com.rocha.api_boleto.dtos.BoletoDTO;
import br.com.rocha.api_boleto.dtos.BoletoRequestDTO;
import br.com.rocha.api_boleto.services.BoletoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boleto")
@AllArgsConstructor
public class BoletoController {

    private final BoletoService boletoService;

    @PostMapping
    public ResponseEntity<BoletoDTO> salvar(@RequestBody BoletoRequestDTO boletoRequestDTO) {
        return new ResponseEntity<>(boletoService.salvar(boletoRequestDTO.getCodigoBarras()), HttpStatus.CREATED);
    }
}
