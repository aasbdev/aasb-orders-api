package br.com.aasb.orders.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aasb.orders.application.dto.OrderDto;
import br.com.aasb.orders.application.service.OrderApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderApplicationService orderApplicationService;
	
	@GetMapping
	@Operation(summary = "Busca todos os pedidos", description = "Busca todos os pedidos", tags = { "Pedido" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = OrderDto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<List<OrderDto>> findAll() {
		return new ResponseEntity<>(orderApplicationService.getAllOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca Pedido por Id", description = "Busca Pedido por Id", tags = { "Pedido" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = OrderDto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
		return new ResponseEntity<>(orderApplicationService.getById(id), HttpStatus.OK);
	}	
	
}
