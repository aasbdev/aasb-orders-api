package br.com.aasb.orders.presentation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import br.com.aasb.orders.ApiApplication;
import br.com.aasb.orders.test.container.DefaultPostgresContainer;

@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@SpringBootTest(classes = { ApiApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseResourceIT extends AbstractResourceIT {

	@Autowired
	protected TestRestTemplate testRestTemplate;
	 	
	final StringBuilder urlAPI = new StringBuilder(RESOURSE_BASE_URL);

	static {
		if (DefaultPostgresContainer.isEnabled()) {
			DefaultPostgresContainer.getInstance().start();
		}
	}

	
	void beforeEach() {
		deleteAll();		
	}

	@AfterAll
	void afterAll() {
		deleteAll();
	}

	void deleteAll() {			
		
	}
}
