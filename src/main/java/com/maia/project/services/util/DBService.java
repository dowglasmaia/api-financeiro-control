package com.maia.project.services.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.project.domain.Category;
import com.maia.project.domain.Entry;
import com.maia.project.domain.User;
import com.maia.project.services.CategoryService;
import com.maia.project.services.EntryService;
import com.maia.project.services.UserService;

/* 
 * Class de Testes
 * */

@Service
public class DBService {


	@Autowired
	private UserService uService;

	@Autowired
	private EntryService etService;

	@Autowired
	private CategoryService catService;

	@Autowired
	private HashUtil hash;


	public void instanciateTestDatabase() throws ParseException {

		// User
				User user1 = new User(null, "Dowglas Maia", LocalDateTime.now(), "dowglasmaia@live.com",
						hash.getSecurityHash("maia"));
				uService.save(user1);

				// category
				Category ct1 = new Category(null, "Lazer", "Gastos fim de Semana");
				catService.save(ct1);

				Category ct2 = new Category(null, "Casa", "Cinema");
				catService.save(ct2);

				Category ct3 = new Category(null, "Restaurante", "Almoço");
				catService.save(ct3);

				Category ct4 = new Category(null, "Combustivel", "Combustivel para viagem");
				catService.save(ct4);

				Category ct5 = new Category(null, "Jogo", "Ferias");
				catService.save(ct5);

				Category ct6 = new Category(null, "Internet", "Internet Movel");
				catService.save(ct6);

				// Entry
				Entry et1 = new Entry(null, "Gas de Cozinha", "", "expense", new BigDecimal("80.00"), LocalDate.now(), true,
						ct1);
				etService.save(et1);

				Entry et2 = new Entry(null, "Pagamento de Gamer GOT", "ok", "revenue", new BigDecimal("800.00"),
						LocalDate.now(), true, ct5);
				etService.save(et2);

				Entry et3 = new Entry(null, "Salario de Empresa X", "Trabalgo Free Lance", "expense", new BigDecimal("1200.00"),
						LocalDate.now(), true, ct3);
				etService.save(et3);

				Entry et4 = new Entry(null, "Cinema", "Filme Maya", "revenue", new BigDecimal("10.59"), LocalDate.now(), true,
						ct4);
				etService.save(et4);

				Entry et5 = new Entry(null, "Viagem de Ferias", "ok", "expense", new BigDecimal("8000.00"), LocalDate.now(),
						true, ct4);
				etService.save(et5);
	}

}
