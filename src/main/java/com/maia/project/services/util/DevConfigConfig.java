package com.maia.project.services.util;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/* 
 * Class de Configuração dos para executar a Class de Testes
 * */

@Configuration
@Profile("dev")
public class DevConfigConfig {

	@Autowired
	private DBService dbService;

	// virificando a chave de configuração do BD
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDataBase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		}

		dbService.instanciateTestDatabase();
		return true;
	}

}
