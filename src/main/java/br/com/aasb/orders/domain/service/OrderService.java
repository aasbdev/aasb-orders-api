package br.com.aasb.orders.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aasb.orders.domain.model.OrderEntity;
import br.com.aasb.orders.domain.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<OrderEntity> findAllOrders() {
		return repository.findAllOrders();
	}
 	
	public Optional<OrderEntity> findOrderById(Long id) {
		return repository.findById(id);
	}
	
	public void saveOrder(OrderEntity order) {
		repository.save(order);
	}
	
	public void updateOrder(OrderEntity order) {
		repository.save(order);
	}
}
