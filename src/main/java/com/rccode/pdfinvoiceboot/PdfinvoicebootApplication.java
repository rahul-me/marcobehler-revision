package com.rccode.pdfinvoiceboot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PdfinvoicebootApplication {

	@Bean
	public InitializingBean runner(DataSource dataSource) {
		return () -> {
			System.out.println("This is the datasource this Spring Boot project is using: " + dataSource);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PdfinvoicebootApplication.class, args);
	}

}
