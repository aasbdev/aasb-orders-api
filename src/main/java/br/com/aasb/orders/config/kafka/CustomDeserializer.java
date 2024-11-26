package br.com.aasb.orders.config.kafka;


import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aasb.orders.infra.message.OrderMessage;

public class CustomDeserializer implements Deserializer<OrderMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderMessage deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), OrderMessage.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to SaleMessage");
        }
    }

}