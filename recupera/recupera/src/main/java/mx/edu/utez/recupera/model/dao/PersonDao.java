package mx.edu.utez.recupera.model.dao;

import mx.edu.utez.recupera.model.entity.PersonBean;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonDao extends CrudRepository<PersonBean, Integer> {

    Optional<PersonBean> findByCurpIgnoreCase(String curp);

    Optional<PersonBean> findByUsername(String username);

}
