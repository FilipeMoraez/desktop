package br.com.desktop.login.repository;

import br.com.desktop.login.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    List<Authority> findAll();
}
