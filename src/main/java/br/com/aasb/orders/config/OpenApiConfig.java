package br.com.aasb.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition( 
		tags = {
	            @Tag(name="Pedidos", description =  "Serviço de gestão de pedidos"),	       
	    },
	    servers = {
	       @Server(url = "/api/v1/", description = "Default Server URL")
	    }
	) 
@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("API de gestão de pedidos")
				.version("v1")
				.description("RESTful API with Java 17 and Spring Boot 3")
				.termsOfService("https://aasbdev.com.br")
				.license(
					new License()
						.name("Apache 2.0")
						.url("https://aasbdev.com.br")
					)
				);
	}
}
