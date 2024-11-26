package br.com.aasb.orders.application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aasb.orders.application.dto.OrderDto;
import br.com.aasb.orders.domain.model.OrderEntity;
import br.com.aasb.orders.domain.model.OrderItemEntity;
import br.com.aasb.orders.domain.service.OrderService;

@Service
public class OrderApplicationService {
	
	@Autowired
	private OrderService orderService;
		
	public List<OrderDto> getAllOrders() {
		return orderService.findAllOrders().stream().map(OrderDto::new).toList();
	}

	public OrderDto getById(Long id) {		
		 OrderEntity entity = orderService.findOrderById(id).orElseThrow(()-> new RuntimeException("Pedido não encontrado"));		 
		 return new OrderDto(entity);
	}

	public void processaOrder(OrderDto order) {
		OrderEntity entity = order.buildOrder();
		entity.setStatus("PROCESSED");
		
		Optional<OrderEntity> pedidoJaCalculado = orderService.findOrderById(order.getOrderId());
		
		if (pedidoJaCalculado.isEmpty()) {		
			BigDecimal totalPrice = entity.getItems().stream()
		            .map(OrderItemEntity::getPrice) 
		            .reduce(BigDecimal.ZERO, BigDecimal::add);    
			
			entity.setTotalAmount(totalPrice);
			orderService.saveOrder(entity);
		}
	}	
	
	
	
}
