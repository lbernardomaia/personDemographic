package com.personDemographic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class PersonDemographicApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PersonDemographicApplication.class);
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(PersonDemographicApplication.class, args);
	}
}