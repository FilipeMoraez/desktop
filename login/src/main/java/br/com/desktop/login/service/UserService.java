package br.com.desktop.login.service;

import static br.com.desktop.login.contants.ErrorMesage.*;

import br.com.desktop.login.dto.UserAuthorityDTO;
import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Authority;
import br.com.desktop.login.model.User;
import br.com.desktop.login.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements AbstractService<User>{

    private UserRepository userRepository;
    private AuthorityService authorityService;

    public UserService(UserRepository repository, AuthorityService authorityService) {
        this.userRepository = repository;
        this.authorityService = authorityService;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User findById(Long id) throws BussinessException{
        return userRepository.findById(id).orElseThrow(() -> new BussinessException(USER_NOT_FOUND));
    }
    @Override
    public void save(User s) throws BussinessException {
        s.setId(null);
        this.valid(s);
        logged(s, s.getId());
        userRepository.save(s);
    }
    @Override
    public void update(User s) throws BussinessException {
        this.validUpdate(s);
        this.logged(s, s.getId());
        userRepository.save(s);
    }
    @Override
    public void deleteById(Long id) throws BussinessException{
        this.findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public void validUpdate(User s) throws BussinessException{
        valid(s);
        if(s.getId() == null || s.getId() <= 0)
            throw new BussinessException(USER_NOT_FOUND);
    }
    @Override
    public void valid(User s) throws BussinessException{
        if(s.getUsername() == null || s.getUsername().isEmpty() || s.getUsername().length()<5)
            throw new BussinessException(USER_LOG);

        if(s.getPassword() == null || s.getPassword().isEmpty() || s.getPassword().length()<5)
            throw new BussinessException(USER_PAS);

        if(s.getEmail() == null || s.getEmail().isEmpty())
            throw new BussinessException(USER_EMAIL_EMPTY);

        if(s.getEmail() != null && !s.getEmail().contains("@"))
            throw new BussinessException(USER_EMAIL_NOT_VALID);

        if(s.getBirthDate() == null)
            throw new BussinessException(USER_BIRTHDAY);
    }

    public void saveAuthorityUser(UserAuthorityDTO userAuthority) throws BussinessException {
        User s = findById(userAuthority.getUser());
        Authority a = authorityService.findById(userAuthority.getAuthority());
        s.setAuthority(a);
        userRepository.save(s);
    }
}
