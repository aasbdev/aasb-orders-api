package br.com.aasb.orders.infra.message;


import java.util.List;

import br.com.aasb.orders.application.dto.OrderDto;
import br.com.aasb.orders.domain.model.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {

    private OrderDto order;

    private OrderEvent event;

}
