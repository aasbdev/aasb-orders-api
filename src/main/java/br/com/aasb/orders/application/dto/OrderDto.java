package br.com.aasb.orders.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.aasb.orders.domain.model.OrderEntity;
import br.com.aasb.orders.domain.model.OrderItemEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class OrderDto implements Serializable {

	private static final long serialVersionUID = -5031372160986504744L;

	private Long orderId;

	private String clientId;
	
	private BigDecimal totalAmount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime orderDate;
	
	private String status;

	private List<OrderItemDto> items; 
	
	public OrderDto(OrderEntity orderEntity) {

		this.orderId = orderEntity.getId();
		this.clientId = orderEntity.getClientId();
		this.totalAmount = orderEntity.getTotalAmount();
		this.status = orderEntity.getStatus();		
		handleItems(orderEntity);		
	}

	private void handleItems(OrderEntity orderEntity) {		
		this.items = orderEntity.getItems().stream().map(OrderItemDto::new).toList();
	}	
	
	public OrderEntity buildOrder() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setClientId(clientId);
		orderEntity.setId(orderId);
		orderEntity.setStatus(status);
		handleOrderItem(orderEntity);
		return orderEntity;
	}

	private void handleOrderItem(OrderEntity orderEntity) {
		List<OrderItemEntity> itemsEntity = new  ArrayList<>();
		
		for (OrderItemDto dto : items) {
			itemsEntity.add(dto.buildOrderItem(orderEntity.getId()));
		}
		
		orderEntity.setItems(itemsEntity);
	}
	
	
}
