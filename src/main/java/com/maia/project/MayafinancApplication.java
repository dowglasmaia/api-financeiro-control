package com.maia.project;

import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MayafinancApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MayafinancApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Definindo o Time-Zone Local - Resolvendo problema com horas no banco de Dados
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));

	}
}
