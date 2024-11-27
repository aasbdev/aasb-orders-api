package br.com.aasb.orders.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aasb.orders.domain.model.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
	