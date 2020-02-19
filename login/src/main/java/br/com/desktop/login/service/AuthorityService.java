package br.com.desktop.login.service;

import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Authority;
import br.com.desktop.login.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.desktop.login.contants.ErrorMesage.*;

@Service
public class AuthorityService implements AbstractService<Authority> {

    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }
    @Override
    public List<Authority> findAll(){
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(Long id) throws BussinessException {
        return authorityRepository.findById(id).orElseThrow(()-> new BussinessException(AUTHORITY_NOT_FOUND));
    }

    public Authority findByName(String name) throws BussinessException{
        return authorityRepository.findByName(name).orElseThrow(()-> new BussinessException(AUTHORITY_NOT_FOUND));
    }

    @Override
    public void save(Authority s) throws BussinessException {
        s.setId(null);
        this.valid(s);
        this.logged(s, s.getId());
        authorityRepository.save(s);
    }

    @Override
    public void update(Authority s) throws BussinessException {
        this.validUpdate(s);
        this.logged(s, s.getId());
        authorityRepository.save(s);
    }

    @Override
    public void deleteById(Long id) throws BussinessException {
        this.findById(id);
        authorityRepository.deleteById(id);
    }

    @Override
    public void validUpdate(Authority s) throws BussinessException {
        valid(s);
        if(s.getId() == null){
            throw new BussinessException(AUTHORITY_NOT_FOUND);
        }

    }

    @Override
    public void valid(Authority s) throws BussinessException {
        if(s.getName() == null || s.getName().isEmpty()){
            throw new BussinessException(AUTHORITY_NAME);
        }

    }
}
