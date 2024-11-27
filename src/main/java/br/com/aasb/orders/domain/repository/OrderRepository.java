package br.com.aasb.orders.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.aasb.orders.domain.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	@Query("SELECT o FROM OrderEntity o JOIN FETCH o.items")
	List<OrderEntity> findAllOrders();

}
	