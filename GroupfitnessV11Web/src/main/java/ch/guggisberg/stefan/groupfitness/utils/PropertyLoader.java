package ch.guggisberg.stefan.groupfitness.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup

public class PropertyLoader {

	private Properties properties;

	@PostConstruct
	public void init() throws IOException {
		InputStream inputStream = this.getClass().getClassLoader()

				.getResourceAsStream("app.properties");

		properties = new Properties();
		System.out.println("InputStream is: " + inputStream);
		// Loading the properties
		properties.load(inputStream);

		// Printing the properties
		System.out.println("Read Properties."+properties);
	}

	public Properties getProperties() {
		return properties;
	}
	

}