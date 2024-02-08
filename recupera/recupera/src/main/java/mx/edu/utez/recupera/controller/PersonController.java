package mx.edu.utez.recupera.controller;

import lombok.AllArgsConstructor;
import mx.edu.utez.recupera.model.dto.PersonDto;
import mx.edu.utez.recupera.model.entity.PersonBean;
import mx.edu.utez.recupera.service.impl.PersonImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v2")
public class PersonController {

    private final PersonImpl personService;

    @GetMapping("/{curp}")
    public ResponseEntity getByCurp(@PathVariable String curp){
        return ResponseEntity.ok(personService.findByCurp(curp));
    }

    @PostMapping("/person")
    public PersonDto create(@RequestBody PersonDto personDto){
        PersonBean personSave = personService.save(personDto);
        return personDto.builder()
                .idperson(personSave.getIdperson())
                .username(personDto.getUsername())
                .nombre(personSave.getNombre())
                .ape1(personSave.getApe1())
                .ape2(personSave.getApe2())
                .fecha_nac(personSave.getFecha_nac())
                .edo_nac(personSave.getEdo_nac())
                .sexo(personSave.getSexo())
                .curp(personSave.getCurp())
                .rfc(personSave.getRfc())
                .password(personSave.getPassword())
                .activo(personSave.isActivo())
                .build();
    }

    @PutMapping("/person")
    public PersonDto update(@RequestBody PersonDto personDto){
        PersonBean personUpdate = personService.save(personDto);
        return personDto.builder()
                .idperson(personUpdate.getIdperson())
                .username(personUpdate.getUsername())
                .nombre(personUpdate.getNombre())
                .ape1(personUpdate.getApe1())
                .ape2(personUpdate.getApe2())
                .fecha_nac(personUpdate.getFecha_nac())
                .edo_nac(personUpdate.getEdo_nac())
                .sexo(personUpdate.getSexo())
                .curp(personUpdate.getCurp())
                .rfc(personUpdate.getRfc())
                .password(personUpdate.getPassword())
                .activo(personUpdate.isActivo())
                .build();
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try{
            PersonBean person = personService.findById(id);
            personService.delete(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (DataAccessException ex){
            response.put("mensaje", ex.getMessage());
            response.put("Person", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/person/{id}")
    public PersonBean showById(@PathVariable Integer id){
        try{
            return personService.findById(id);
        } catch (DataAccessException dae){
            throw new RuntimeException("Error en Base de Datos: ", dae);
        } catch (Exception ex){
            throw new RuntimeException("Error al obtener la persona: ", ex);
        }
    }

    @GetMapping("/person")
    public List<PersonBean> findAll(){
        try{
            return personService.findAll();
        } catch (DataAccessException dae){
            throw new RuntimeException("Error en la base de datos: ", dae);
        } catch (Exception ex){
            throw new RuntimeException("Error al obtener la persona: ", ex);
        }

    }
}
