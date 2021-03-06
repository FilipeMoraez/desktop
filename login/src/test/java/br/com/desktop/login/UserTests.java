package br.com.desktop.login;

import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.User;
import br.com.desktop.login.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;

@SpringBootTest
@Sql("classpath:initUserTest.sql")
class UserTests {
	
	private Logger logger = LoggerFactory.getLogger(UserTests.class);

	private final String ERR = "test";
	@Autowired
	private UserService service;

	@Test()
	void testUser() {
		saveUserFilipe();
		listUser();
		updateUser();
		deleteUser();
	}

	private void deleteUser() {
		try{
			service.deleteById(1l);
		}catch(BussinessException e){
			logger.info("Delete do usuário não está funcionando.");
		}
	}

	private void updateUser() {
		try{
			User x = service.findById(2l);
			User s = new User();
			s.setId(x.getId());
			s.setUsername("MatheusRoberto");
			s.setEmail("joaquim@hotmail.com");
			s.setBirthDate(new Date());
			s.setPassword( "4324312");
			s.setCreate(new Date());
			s.setModify(new Date());
			service.update(s);
			logger.info("Data de modificacao : " + s.getModify());
		}catch(BussinessException e){
			logger.info("Update do usuário não está funcionando.");
		}
	}


	private void listUser() {
		try{

			User u = service.findById(1l);
			List<User> lista = service.findAll();
			if(lista.size()<4 && u != null & u.getId() == 1l){
				throw new RuntimeException("Listagem não funcionando corretamente ou busca por 1 não funcionando corretamente");
			}
		}catch(BussinessException e){
			throw new RuntimeException("Listagem não funcionando corretamente ou busca por 1 não funcionando corretamente");
		}
	}

	private void saveUserFilipe(){
		User s = new User();
		s.setUsername("Filipe_moraes");
		s.setEmail("fiipe_Antique@hotmail.com");
		s.setBirthDate(new Date());
		s.setPassword( "123456");
		s.setCreate(new Date());
		s.setModify(new Date());
		s.setAuthorityName("ADMIN");

		validaNome(s);
		validaEmail(s);
		validaPassword(s);
		validaEmailEmpty(s);
		validaDataNasc(s);
		try{
			service.save(s);
			logger.info("Data de criação: " + s.getCreate());
		}catch(BussinessException e){
			throw new RuntimeException(e);
		}
	}



	private void validaNome(User s) {
		s.setUsername(ERR);
		try{
			service.save(s);
			throw new RuntimeException("Validação de nome não está funcionando");
		}catch(Exception e){
			logger.info("Nome validado com sucesso");
		}

		s.setUsername(null);
		try{
			service.save(s);
			throw new RuntimeException("Validação de nome não está funcionando");
		}catch(DataIntegrityViolationException e){
			throw new RuntimeException(e);
		}catch(Exception e){
			logger.info("Nome nulo validado com sucesso");
			s.setUsername("Filipe_Moraes");
		}
	}
	private void validaEmail(User s) {
		s.setEmail(ERR);
		try{
			service.save(s);
			throw new RuntimeException("Validação de email não está funcionando");
		}catch(BussinessException e){
			logger.info("Email validado com sucesso");
			s.setEmail("fiipe_antique@hotmail.com");
		}
	}
	private void validaPassword(User s) {
		s.setPassword(ERR);
		try{
			service.save(s);
			throw new RuntimeException("Validação de senha não está funcionando");
		}catch(BussinessException e){
			logger.info("Senha validado com sucesso");
		}

		s.setPassword(null);
		try{
			service.save(s);
			throw new RuntimeException("Validação de senha não está funcionando");
		}catch(DataIntegrityViolationException e){
			throw new RuntimeException(e);
		}catch(BussinessException e){
			logger.info("Senha nula validado com sucesso");
			s.setPassword("TestePassword");
		}
	}
	private void validaEmailEmpty(User s) {
		s.setEmail(null);
		try {
			service.save(s);
			throw new RuntimeException("Validação de email vazio não está funcionando");
		}catch(DataIntegrityViolationException e){
			throw new RuntimeException("Validação de email null não está funcionando");
		}catch(BussinessException e){
			logger.info("Email vazio validado com sucesso");
			s.setEmail("filipe_antique@hotmail.com");
		}
	}

	private void validaDataNasc(User s) {
		s.setBirthDate(null);
		try{
			service.save(s);
			throw new RuntimeException("Validação de data nascimento não está funcionando");
		}catch(BussinessException  e){
			logger.info("Data nascimento validado com sucesso");
			s.setBirthDate(new Date());
		}
	}
}
