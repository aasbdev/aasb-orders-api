package br.com.aasb.orders.config.kafka;


import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.aasb.orders.infra.message.OrderMessage;

public class CustomSerializer implements Serializer<OrderMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, OrderMessage orderMessage) {
        try {
            if (orderMessage == null){
                return null;
            }
            objectMapper.registerModule(new JavaTimeModule());
	        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Para usar o formato ISO padrão
	        orderMessage.getOrder().setOrderDate(null);
	        return objectMapper.writeValueAsBytes(orderMessage);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing SaleMessage to byte[]");
        }
    }

}
