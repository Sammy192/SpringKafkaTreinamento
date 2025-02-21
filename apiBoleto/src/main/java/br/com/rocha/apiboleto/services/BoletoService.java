package br.com.rocha.apiboleto.services;

import br.com.rocha.apiboleto.dtos.BoletoDTO;
import br.com.rocha.apiboleto.entities.BoletoEntity;
import br.com.rocha.apiboleto.entities.enums.SituacaoBoletoEnum;
import br.com.rocha.apiboleto.mapper.BoletoMapper;
import br.com.rocha.apiboleto.repositories.BoletoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
//@AllArgsConstructor(onConstructor = @__(@Autowired))
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
