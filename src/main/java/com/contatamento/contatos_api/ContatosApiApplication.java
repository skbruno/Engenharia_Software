package com.contatamento.contatos_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.contatamento.contatos_api")
@EnableJpaRepositories("com.contatamento.contatos_api.Interfaces")
public class ContatosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContatosApiApplication.class, args);
	}

}
