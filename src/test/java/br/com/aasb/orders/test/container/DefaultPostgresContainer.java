package br.com.aasb.orders.test.container;


import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.org.apache.commons.lang3.StringUtils;

public class DefaultPostgresContainer extends PostgreSQLContainer<DefaultPostgresContainer> {

	private static boolean enabled = Boolean.parseBoolean(StringUtils.defaultIfBlank(System.getProperties().getProperty("testcontainer.enabled"), "true"));
	
	private static String imageVersion = StringUtils.defaultIfBlank(System.getProperties().getProperty("testcontainer.postgres.image"), "postgres:11.4-alpine");
	
	private static DefaultPostgresContainer container;

	public static DefaultPostgresContainer getInstance() {
		if (container == null) {
			container = new DefaultPostgresContainer();
			container.withStartupTimeoutSeconds(600);
		}
		
		return container;
	}	
	

	private DefaultPostgresContainer() {
		super(getImageVersion());
	}
	
	@Override
	public void start() {
		super.start();
		System.setProperty("DATABASE_POSTGRES_URL", container.getJdbcUrl());
		System.setProperty("DATABASE_POSTGRES_NAME", container.getDatabaseName());
		System.setProperty("DATABASE_POSTGRES_USERNAME", container.getUsername());
		System.setProperty("DATABASE_POSTGRES_PASSWORD", container.getPassword());
		System.setProperty("DATABASE_JPA_DDL_AUTO", "create-drop");
		System.setProperty("DATABASE_JPA_GENERATE_DDL", "true");
		System.setProperty("DATABASE_JPA_SHOW_SQL", "true");
	}
	
	@Override
	public void stop() {
		
	}
	
	public static boolean isEnabled() {
		return enabled;
	}

	public static String getImageVersion() {
		return imageVersion;
	}
	
}
