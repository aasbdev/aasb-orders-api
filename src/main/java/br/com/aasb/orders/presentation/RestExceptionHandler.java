package br.com.aasb.orders.presentation;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.aasb.orders.infra.exception.BusinessException;
import br.com.aasb.orders.infra.exception.ErrorResponse;
import br.com.aasb.orders.infra.exception.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

	private ResponseEntity<ErrorResponse> createResponseEntity(final Exception exception, final WebRequest request,
			final HttpStatus status) {
		ErrorResponse error = ErrorResponse.builder() //
				.message(exception.getMessage()) //
				.httpStatus(status.value())
				.details(request.getDescription(false)) //
				.timestamp(LocalDateTime.now()) //
				.build();

		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBAuthenticationExceptionusiness(final BusinessException ex,
			final WebRequest request) {
		ErrorResponse error = ErrorResponse.builder() //
				.message(ex.getMessage()) //
				.details(request.getDescription(false)) //
				.timestamp(LocalDateTime.now()) // )
				.fieldErrors(ex.getFieldErrors()) //
				.build();
		return new ResponseEntity<>(error, ex.getHttpStatus());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(final IllegalArgumentException ex,
			final WebRequest request) {
		return createResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFound(final RecordNotFoundException ex,
			final WebRequest request) {
		return createResponseEntity(ex, request, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> otherErrors(final Exception ex, final WebRequest request) {
		log.error(ex.getMessage(), ex);
		return createResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}