package br.com.aasb.orders.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItemEntity {

	@Id	
	private Long id;
	
	@Column(name="product_name")
	private String productName;
	
	private BigDecimal price;
	
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;
}
