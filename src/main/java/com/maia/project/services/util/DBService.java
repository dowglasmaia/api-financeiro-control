package com.maia.project.services.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maia.project.domain.Category;
import com.maia.project.domain.Entry;
import com.maia.project.domain.Perfil;
import com.maia.project.domain.Usuario;
import com.maia.project.repository.CategoryRepository;
import com.maia.project.repository.EntryRespository;
import com.maia.project.repository.UsuarioRepository;

/* 
 * Class de Testes
 * */

@Service
public class DBService {

	@Autowired
	private EntryRespository etService;

	@Autowired
	private CategoryRepository catService;

	@Autowired
	private UsuarioRepository userServices;

	@Autowired
	private BCryptPasswordEncoder bc;

	public void instanciateTestDatabase() throws ParseException {

		Usuario u = new Usuario(null, "Dowglas Maia", "dowglasmaia@live.com", bc.encode("maia"));		
		u.addPerfil(Perfil.ADMIN);
		userServices.save(u);
		
		Usuario u2 = new Usuario(null, "Dowglas", "dowglasmaia@gmail.com", bc.encode("maia"));		
		userServices.save(u2);

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
