package br.com.chocode.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackApplication {
	private static final Logger LOG = LoggerFactory.getLogger(BackApplication.class);

	public static void main(String[] args) {
		LOG.info("Iniciando Api Chocode.");
		SpringApplication.run(BackApplication.class, args);
		LOG.info("Api Chocode iniciada com sucesso.");
	}

}
