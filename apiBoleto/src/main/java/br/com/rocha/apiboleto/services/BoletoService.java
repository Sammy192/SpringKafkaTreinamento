package br.com.rocha.apiboleto.services;

import br.com.rocha.apiboleto.dtos.BoletoDTO;
import br.com.rocha.apiboleto.entities.BoletoEntity;
import br.com.rocha.apiboleto.entities.enums.SituacaoBoletoEnum;
import br.com.rocha.apiboleto.mapper.BoletoMapper;
import br.com.rocha.apiboleto.repositories.BoletoRepository;
import br.com.rocha.apiboleto.services.exceptions.ApplicationException;
import br.com.rocha.apiboleto.services.exceptions.ResourceNotFoundException;
import br.com.rocha.apiboleto.services.kafka.BoletoProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BoletoService {
    
    private BoletoRepository boletoRepository;
    private BoletoProducer boletoProducer;
    
    public BoletoDTO salvar(String codigoBarras) {
        if (boletoRepository.findByCodigoBarras(codigoBarras).isPresent()) {
            throw new ApplicationException("Já existe uma solicitação de pagamento para esse boleto.");
        }
        
        BoletoEntity boletoEntity = BoletoEntity.builder()
                .codigoBarras(codigoBarras)
                .situacaoBoleto(SituacaoBoletoEnum.INICIALIZADO)
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();
        
        boletoRepository.save(boletoEntity);

        boletoProducer.enviarMensagem(BoletoMapper.toAvro(boletoEntity));
        return BoletoMapper.toDto(boletoEntity);
    }

    public BoletoDTO buscarBoletoPorCodigoBarras(String codigoBarras) {
        return BoletoMapper.toDto(recuperaBoleto(codigoBarras));
    }

    private BoletoEntity recuperaBoleto(String codigoBarras) {
        return boletoRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new ResourceNotFoundException("Boleto não encontrado"));
    }

    public void atualizar(BoletoEntity boleto) {
        var boletoAtual = recuperaBoleto(boleto.getCodigoBarras());

        boletoAtual.setSituacaoBoleto(boleto.getSituacaoBoleto());
        boletoAtual.setDataAtualizacao(LocalDateTime.now());
        boletoRepository.save(boletoAtual);
    }
}
