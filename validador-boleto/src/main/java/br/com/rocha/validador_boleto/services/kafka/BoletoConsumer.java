package br.com.rocha.validador_boleto.services.kafka;

import br.com.rocha.avro.Boleto;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BoletoConsumer implements ConsumerSeekAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
    public void consomeBoleto(Boleto boleto) throws InterruptedException {
        Thread.sleep(3000);
        LOGGER.info("Novo boleto recebido: {}", boleto);
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        //ve apenas mensagens novas
        assignments.forEach((t, o) -> callback.seekToEnd(t.topic(), t.partition()));
    }
}
