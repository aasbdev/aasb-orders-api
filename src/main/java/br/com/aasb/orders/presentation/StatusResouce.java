package br.com.aasb.orders.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusResouce {

	@GetMapping
	public String getStatus() {
		return "ok";
	}	
	
}
