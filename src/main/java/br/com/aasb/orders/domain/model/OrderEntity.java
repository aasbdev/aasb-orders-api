package br.com.aasb.orders.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

	@Id
	private Long id;
	
	@Column(name="client_id")
	private String clientId;
	
	@Column(name="total_amount")
	private BigDecimal totalAmount;
	
	private String status; // PENDING, PROCESSED, SENT

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<OrderItemEntity> items;
}