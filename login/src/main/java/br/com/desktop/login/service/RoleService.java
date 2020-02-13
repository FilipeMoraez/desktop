package br.com.desktop.login.service;

import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Role;
import br.com.desktop.login.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.desktop.login.contants.ErrorMesage.*;

@Service
public class RoleService implements AbstractService<Role>{

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) throws BussinessException {
        return roleRepository.findById(id).orElseThrow(()-> new BussinessException(ROLE_NOT_FOUND));
    }

    @Override
    public void save(Role s) throws BussinessException {
        s.setId(null);
        this.valid(s);
        this.logged(s, s.getId());
        roleRepository.save(s);
    }

    @Override
    public void update(Role s) throws BussinessException {
        this.validUpdate(s);
        this.logged(s, s.getId());
        roleRepository.save(s);
    }

    @Override
    public void deleteById(Long id) throws BussinessException {
        this.findById(id);
        roleRepository.deleteById(id);
    }

    @Override
    public void validUpdate(Role s) throws BussinessException {
        valid(s);
        if(s.getId() == null){
            throw new BussinessException(ROLE_NOT_FOUND);
        }

    }

    @Override
    public void valid(Role s) throws BussinessException {
        if(s.getName() == null || s.getName().isEmpty()){
            throw new BussinessException(ROLE_NAME);
        }
    }

}

