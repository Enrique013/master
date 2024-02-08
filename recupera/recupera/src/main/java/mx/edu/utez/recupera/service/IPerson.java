package mx.edu.utez.recupera.service;

import jakarta.transaction.Transactional;
import mx.edu.utez.recupera.model.dto.PersonDto;
import mx.edu.utez.recupera.model.entity.PersonBean;

import java.util.List;

public interface IPerson {

    PersonBean save(PersonDto personDto);

    boolean isUsernameTaken(String username);

    PersonBean findById(Integer id);

    void delete(PersonBean personBean);

    List<PersonBean> findAll();

    PersonBean findByCurp(String curp);

}
