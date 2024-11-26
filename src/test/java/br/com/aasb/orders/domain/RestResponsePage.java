package br.com.aasb.orders.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class RestResponsePage<T> extends PageImpl<T> {

	
	private static final long serialVersionUID = -7337188331787266963L;

	public RestResponsePage(List<T> content) {
		super(content);
	}
	
	public RestResponsePage(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}
	
	@JsonCreator(mode= JsonCreator.Mode.PROPERTIES)
	public RestResponsePage(@JsonProperty("content") final List<T> content, //
			@JsonProperty("number") final int number, //
			@JsonProperty("size") final int size, //
			@JsonProperty("totalElements") final Long totalElements,//
			@JsonProperty("pageable") final JsonNode pageable,//
			@JsonProperty("last") final boolean last,//
			@JsonProperty("totalPages") final int  totalPages,//
			@JsonProperty("sort") final JsonNode sort,//
			@JsonProperty("first") final boolean first,//
			@JsonProperty("numberOfElements") final int numberOfElements) {
		super(content == null ? new ArrayList<>() : content, PageRequest.of(number, size < 1 ? 1 : size), totalElements == null ? 0 : totalElements);
	}
	

}
