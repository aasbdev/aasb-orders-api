package br.com.aasb.orders.infra.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {

	private String message;
	
	private String details;
	
	private Integer httpStatus;
	
	private LocalDateTime timestamp;
	
	private List<FieldError> fieldErrors;
}
