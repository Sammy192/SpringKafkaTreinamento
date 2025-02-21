package br.com.rocha.api_boleto.services;

import br.com.rocha.api_boleto.dtos.BoletoDTO;
import br.com.rocha.api_boleto.entities.BoletoEntity;
import br.com.rocha.api_boleto.entities.enums.SituacaoBoletoEnum;
import br.com.rocha.api_boleto.mapper.BoletoMapper;
import br.com.rocha.api_boleto.repositories.BoletoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BoletoService {
    
    private BoletoRepository boletoRepository;
    
    public BoletoDTO salvar(String codigoBarras) {
        if (boletoRepository.findByCodigoBarras(codigoBarras).isPresent()) {
            throw new RuntimeException("Já existe uma solicitação de pagamento para esse boleto.");
        }
        
        BoletoEntity boletoEntity = BoletoEntity.builder()
                .codigoBarras(codigoBarras)
                .situacaoBoleto(SituacaoBoletoEnum.INICIALIZADO)
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();
        
        boletoRepository.save(boletoEntity);

        return BoletoMapper.toDto(boletoEntity);
    }
}
