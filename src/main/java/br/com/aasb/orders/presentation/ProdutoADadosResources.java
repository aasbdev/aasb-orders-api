package br.com.aasb.orders.presentation;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.aasb.orders.application.dto.OrderDto;
import br.com.aasb.orders.domain.model.OrderEvent;
import br.com.aasb.orders.infra.message.OrderMessage;

public class ProdutoADadosResources {

	@Autowired
	private KafkaTemplate<String, OrderMessage> kafkaTemplate;
	
	
	@PostMapping
	public void sendOrdersProdutoAKafka() {

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = StatusResouce.class.getResourceAsStream("/test_orders.json");
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			List<OrderDto> list = objectMapper.readValue(inputStream, new TypeReference<List<OrderDto>>() {
			});
			for (OrderDto orderDto : list) {
				var saleMessage = new OrderMessage(orderDto, OrderEvent.CREATED_ORDER);
				kafkaTemplate.send("tp-orders", saleMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
