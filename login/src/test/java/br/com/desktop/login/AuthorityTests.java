package br.com.desktop.login;

import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Authority;
import br.com.desktop.login.service.AuthorityService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AuthorityTests {

    private Logger logger = LoggerFactory.getLogger(AuthorityTests.class);
    
    @Autowired
    private AuthorityService service;
    
    @Test
    void testAuthority(){
        testSaveAuthority();
        Authority x = testListAuthority();
        updateAuthority(x);
        deleteAuthority();
    }

    private void deleteAuthority() {
        try{
            service.deleteById(1l);
        }catch(BussinessException e){
            throw new RuntimeException("Erro ao tentar deletar Authority");
        }
    }

    private Authority testListAuthority() {
        try {
            return service.findById(1l);
        }catch(BussinessException e) {
            throw new RuntimeException("Listagem nao funcionando");
        }
    }

    private void updateAuthority(Authority x) {
        x.setName("USER");
        try{
            service.update(x);
        }catch(BussinessException e){
            throw new RuntimeException("Atualiação funcionando");
        }
    }

    private void testSaveAuthority()  {
        Authority r = new Authority();
        r.setName("ADMIN");
        r.setCreate(new Date());
        r.setModify(new Date());
        testNameNull(r);
        try {
            service.save(r);
        }catch (BussinessException e){
            throw new RuntimeException("Erro ao tentar cadastrar Authority.");
        }
    }
    private void testNameNull(Authority r){
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
