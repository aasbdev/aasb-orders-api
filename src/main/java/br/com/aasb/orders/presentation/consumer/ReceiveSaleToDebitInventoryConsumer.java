package br.com.aasb.orders.presentation.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.aasb.orders.application.service.OrderApplicationService;
import br.com.aasb.orders.domain.model.OrderEvent;
import br.com.aasb.orders.infra.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReceiveSaleToDebitInventoryConsumer {

	@Autowired
	private OrderApplicationService orderApplicationService;

    @KafkaListener(topics = "tp-orders", groupId = "orders-update")
    public void receive(OrderMessage orderMessage) {
        if(OrderEvent.CREATED_ORDER.equals(orderMessage.getEvent())) {
            log.info("Início do recebimento dos pedidos");
            orderApplicationService.processaOrder(orderMessage.getOrder());
            log.info("Fim do recebimento dos pedidos.");
        }
    }

}
