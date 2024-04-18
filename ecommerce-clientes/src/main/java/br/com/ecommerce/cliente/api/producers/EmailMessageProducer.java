package br.com.ecommerce.cliente.api.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailMessageProducer {

	@Value("${topic.name.producer}")
	private String topico;
	
	
	//Executar o apacher Kafka	
	private final KafkaTemplate<String, String>kafkaTemplate;
	
	//Método para disparar a mensagem para a fila
	public void send(String message) {
		kafkaTemplate.send(topico,message);
	}
	
}
