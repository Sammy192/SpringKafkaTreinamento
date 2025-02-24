package br.com.rocha.validador_boleto.services;

import br.com.rocha.validador_boleto.entities.BoletoEntity;
import br.com.rocha.validador_boleto.entities.enums.SituacaoBoletoEnum;
import br.com.rocha.validador_boleto.mapper.BoletoMapper;
import br.com.rocha.validador_boleto.repositories.BoletoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PagarBoletoService {

    private final BoletoRepository boletoRepository;
    private final NotificacaoProducer notificacaoProducer;

    @SneakyThrows
    public void pagar(BoletoEntity boleto) {
        Thread.sleep(10000);
        String codigoBarrasNumeros = boleto.getCodigoBarras().replaceAll("[^0-9]", "");
        if (codigoBarrasNumeros.length() > 47) {
            complementarBoletoErro(boleto);
        } else {
            complementarBoletoSuccesso(boleto);
        }

        boletoRepository.save(boleto);
        notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
    }

    private void complementarBoletoErro(BoletoEntity boleto) {
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoletoEnum.ERRO_PAGAMENTO);
    }

    private void complementarBoletoSuccesso(BoletoEntity boleto) {
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoletoEnum.PAGO);
    }
}
