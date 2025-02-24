package br.com.rocha.apiboleto.services.kafka;

import br.com.rocha.apiboleto.mapper.BoletoMapper;
import br.com.rocha.apiboleto.services.BoletoService;
import br.com.rocha.avro.Boleto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoConsumer.class);
    private final BoletoService boletoService;

    @KafkaListener(topics = "${spring.kafka.topico-notificacao}")
    public void consumer(@Payload Boleto boleto) {
        LOGGER.info(String.format("Consumindo notificacao -> %s", boleto));
        boletoService.atualizar(BoletoMapper.toEntity(boleto));

    }
}
