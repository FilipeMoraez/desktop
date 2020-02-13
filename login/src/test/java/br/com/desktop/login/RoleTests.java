package br.com.desktop.login;

import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.User;
import br.com.desktop.login.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.List;

@SpringBootTest
class RoleTests {

	private final String ERR = "test";
	@Autowired
	private UserService service;


	@Test()
	void testeUser() {
		saveUserFilipe();
		listUser();
		updateUser();
		deleteUser();
	}

	private void deleteUser() {
		try{
			service.deleteById(1l);
		}catch(BussinessException e){
			System.out.println("Delete do usuário não está funcionando.");
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
			System.out.println("Data de modificacao : " + s.getModify());
		}catch(BussinessException e){
			System.out.println("Update do usuário não está funcionando.");
		}
	}


	private void listUser() {
		try{
			saveUser("MatheusJosias");
			saveUser("JoséMatias");
			saveUser("MathiasJoao");
			User u = service.findById(1l);
			List<User> lista = service.findAll();
			if(lista.size()<4 && u != null & u.getId() == 1l){
				throw new RuntimeException("Listagem não funcionando corretamente ou busca por 1 não funcionando corretamente");
			}
		}catch(BussinessException e){
			throw new RuntimeException("Listagem não funcionando corretamente ou busca por 1 não funcionando corretamente");
		}
	}

	private void saveUser(String name){
		try{
			User s = new User();
			s.setUsername(name);
			s.setEmail("fiipe_Antique@hotmail.com");
			s.setBirthDate(new Date());
			s.setPassword( "123456");
			s.setCreate(new Date());
			s.setModify(new Date());
			service.save(s);
		}catch(BussinessException e){
			throw new RuntimeException("Cadastrar novo usuário não funcionando corretamente ou busca por 1 não funcionando corretamente");
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

		validaNome(s);
		validaEmail(s);
		validaPassword(s);
		validaEmailEmpty(s);
		validaDataNasc(s);
		try{
			service.save(s);
			System.out.println("Data de criação: " + s.getCreate());
		}catch(BussinessException e){
			throw new RuntimeException("Cadastrar novo usuário não funcionando corretamente ou busca por 1 não funcionando corretamente");
		}
	}



	private void validaNome(User s) {
		s.setUsername(ERR);
		try{
			service.save(s);
			throw new RuntimeException("Validação de nome não está funcionando");
		}catch(Exception e){
			System.out.println("Nome validado com sucesso");
		}

		s.setUsername(null);
		try{
			service.save(s);
			throw new RuntimeException("Validação de nome não está funcionando");
		}catch(DataIntegrityViolationException e){
			throw new RuntimeException("Validação de nome null não está funcionando");
		}catch(Exception e){
			System.out.println("Nome nulo validado com sucesso");
			s.setUsername("Filipe_Moraes");
		}
	}
	private void validaEmail(User s) {
		s.setEmail(ERR);
		try{
			service.save(s);
			throw new RuntimeException("Validação de email não está funcionando");
		}catch(BussinessException e){
			System.out.println("Email validado com sucesso");
			s.setEmail("fiipe_antique@hotmail.com");
		}
	}
	private void validaPassword(User s) {
		s.setPassword(ERR);
		try{
			service.save(s);
			throw new RuntimeException("Validação de senha não está funcionando");
		}catch(BussinessException e){
			System.out.println("Senha validado com sucesso");
		}

		s.setPassword(null);
		try{
			service.save(s);
			throw new RuntimeException("Validação de senha não está funcionando");
		}catch(DataIntegrityViolationException e){
			throw new RuntimeException("Validação de senha null não está funcionando");
		}catch(BussinessException e){
			System.out.println("Senha nula validado com sucesso");
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
			System.out.println("Email vazio validado com sucesso");
			s.setEmail("filipe_antique@hotmail.com");
		}
	}

	private void validaDataNasc(User s) {
		s.setBirthDate(null);
		try{
			service.save(s);
			throw new RuntimeException("Validação de data nascimento não está funcionando");
		}catch(BussinessException  e){
			System.out.println("Data nascimento validado com sucesso");
			s.setBirthDate(new Date());
		}
	}
}
