package br.com.rocha.validador_boleto.services;

import br.com.rocha.validador_boleto.entities.BoletoEntity;
import br.com.rocha.validador_boleto.entities.enums.SituacaoBoletoEnum;
import br.com.rocha.validador_boleto.mapper.BoletoMapper;
import br.com.rocha.validador_boleto.repositories.BoletoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ValidarBoletoService {

    private final BoletoRepository boletoRepository;
    private final NotificacaoProducer notificacaoProducer;

    private final PagarBoletoService pagarBoletoService;

    public void validar(BoletoEntity boleto) {
        var codigo = Integer.parseInt(boleto.getCodigoBarras().substring(0,1));
        if (codigo % 2 == 0) {
            complementarBoletoErro(boleto);
            boletoRepository.save(boleto);
            notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
        } else {
            complementarBoletoSucesso(boleto);
            boletoRepository.save(boleto);
            notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
            pagarBoletoService.pagar(boleto);
        }
    }

    private void complementarBoletoErro(BoletoEntity boleto) {
        boleto.setDataCriacao(LocalDateTime.now());
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoletoEnum.ERRO_VALIDACAO);
    }

    private void complementarBoletoSucesso(BoletoEntity boleto) {
        boleto.setDataCriacao(LocalDateTime.now());
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoletoEnum.VALIDADO);
    }
}
