package br.com.rocha.validador_boleto.services;

import br.com.rocha.avro.Boleto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoProducer {

    @Value("${spring.kafka.topico-notificacao}")
    public String topico;

    private final KafkaTemplate<String, Boleto> kafkaTemplate;

    public void enviarMensagem(Boleto boleto) {
        kafkaTemplate.send(topico, boleto);
    }
}
