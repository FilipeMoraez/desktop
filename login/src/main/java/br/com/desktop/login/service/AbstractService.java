package br.com.desktop.login.service;

import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Logged;
import br.com.desktop.login.model.User;

import java.util.Date;
import java.util.List;


public interface AbstractService<T extends Logged> {
    List<T> findAll();
    T findById(Long id) throws BussinessException;
    void save(T s) throws BussinessException;
    void update(T s) throws BussinessException;
    void deleteById(Long id) throws BussinessException;
    void validUpdate(T s) throws BussinessException;
    void valid(T s) throws BussinessException;
    default void logged(T s, Long id) throws BussinessException {
        Date create = null;
        if(id != null) create = findById(id).getCreate();
        s.createLogged(create);
    }
}
