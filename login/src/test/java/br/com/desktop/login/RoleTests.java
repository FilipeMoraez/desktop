package br.com.desktop.login;

import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Role;
import br.com.desktop.login.service.RoleService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class RoleTests {

	private Logger logger = LoggerFactory.getLogger(RoleTests.class);
	
	@Autowired
	private RoleService service;


	@Test()
	void testRole() {
		testSaveRole();
		Role x = testListRole();
		updateRole(x);
		deleteRole();
	}

	private void deleteRole() {
		try{
			service.deleteById(1l);
		}catch(BussinessException e){
			throw new RuntimeException("Erro ao tentar deletar role");
		}
	}

	private Role testListRole() {
		try {
			return service.findById(1l);
		}catch(BussinessException e) {
			throw new RuntimeException("Listagem nao funcionando");
		}
	}

	private void updateRole(Role x) {
		x.setName("USER");
		try{
			 service.update(x);
		}catch(BussinessException e){
			throw new RuntimeException("Atualiação funcionando");
		}
	}

	private void testSaveRole()  {
		Role r = new Role();
		r.setName("ADMIN");
		r.setActive(true);
		r.setCreate(new Date());
		r.setModify(new Date());
		testNameNull(r);
		try {
			service.save(r);
		}catch (BussinessException e){
			throw new RuntimeException("Erro ao tentar cadastrar role.");
		}
	}
	private void testNameNull(Role r){
		r.setName(null);
		try{
			service.save(r);
			throw new RuntimeException("Validação de nome nullo não está funcionando");
		}catch(BussinessException e){
			logger.info("Teste nome nulo validado com sucessso!");
			r.setName("ADMIN");
		}
	}




}
