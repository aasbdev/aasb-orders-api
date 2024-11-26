package br.com.aasb.orders.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aasb.orders.domain.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
	