package br.com.aasb.orders.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class OrderItemDto implements Serializable {
	
	private static final long serialVersionUID = 8872419549529983104L;

	private Long itemId;

	private String productName;

	private BigDecimal price;

	private Integer quantity;

	public OrderItemDto(OrderItemEntity item) {
		this.itemId = item.getId();
		this.productName  = item.getProductName();
		this.price = item.getPrice();
		this.quantity = item.getQuantity();
	}

	public OrderItemEntity buildOrderItem(Long orderId) {
		var orderItemEntity = new  OrderItemEntity();
		orderItemEntity.setId(itemId);
		orderItemEntity.setProductName(productName);
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(orderId);
		orderItemEntity.setOrder(orderEntity);
		orderItemEntity.setPrice(price);
		orderItemEntity.setQuantity(quantity);
		return orderItemEntity;
	}
}
