package br.com.rocha.apiboleto.controllers;

import br.com.rocha.apiboleto.dtos.BoletoDTO;
import br.com.rocha.apiboleto.dtos.BoletoRequestDTO;
import br.com.rocha.apiboleto.services.BoletoService;
import jakarta.validation.Valid;
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
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BoletoController {

    private final BoletoService boletoService;

    @PostMapping
    public ResponseEntity<BoletoDTO> salvar(@Valid @RequestBody BoletoRequestDTO boletoRequestDTO) {
        return new ResponseEntity<>(boletoService.salvar(boletoRequestDTO.getCodigoBarras()), HttpStatus.CREATED);
    }
}
