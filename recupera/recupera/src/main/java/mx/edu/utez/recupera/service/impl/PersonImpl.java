package mx.edu.utez.recupera.service.impl;

import lombok.AllArgsConstructor;
import mx.edu.utez.recupera.model.dao.PersonDao;
import mx.edu.utez.recupera.model.dto.PersonDto;
import mx.edu.utez.recupera.model.entity.PersonBean;
import mx.edu.utez.recupera.service.IPerson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PersonImpl implements IPerson {

    private final PersonDao personDao;

    @Transactional
    @Override
    public PersonBean save(PersonDto personDto){
        if (isUsernameTaken(personDto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        PersonBean person = PersonBean.builder()
                .idperson(personDto.getIdperson())
                .username(personDto.getUsername())
                .nombre(personDto.getNombre())
                .ape1(personDto.getApe1())
                .ape2(personDto.getApe2())
                .fecha_nac(personDto.getFecha_nac())
                .edo_nac(personDto.getEdo_nac())
                .sexo(personDto.getSexo())
                .curp(generarCurp(personDto))
                .password(genRandomPass())
                .rfc(generarRFC(personDto))
                .activo(personDto.isActivo())
                .build();
        return personDao.save(person);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUsernameTaken(String username) {
        // Verificar si existe un usuario con el mismo nombre de usuario
        return personDao.findByUsername(username).isPresent();
    }


    @Transactional
    @Override
    public PersonBean findById(Integer id){
        return personDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(PersonBean personBean){
        personDao.delete(personBean);
    }

    @Transactional
    @Override
    public List<PersonBean> findAll(){
        return (List<PersonBean>) personDao.findAll();
    }

    @Transactional
    @Override
    public PersonBean findByCurp(String curp){
        return personDao.findByCurpIgnoreCase(curp).get();
    }


    public String generarCurp(PersonDto personDto){
        String curp = "";
        curp = String.valueOf(personDto.getApe1().charAt(0));
        curp += String.valueOf(personDto.getApe1().charAt(1));
        curp += String.valueOf(personDto.getApe2().charAt(0));
        curp += String.valueOf(personDto.getNombre().charAt(0));
        curp += String.valueOf(personDto.getFecha_nac().charAt(2));
        curp += String.valueOf(personDto.getFecha_nac().charAt(3));
        curp += String.valueOf(personDto.getFecha_nac().charAt(5));
        curp += String.valueOf(personDto.getFecha_nac().charAt(6));
        curp += String.valueOf(personDto.getFecha_nac().charAt(8));
        curp += String.valueOf(personDto.getFecha_nac().charAt(9));
        curp += String.valueOf(personDto.getSexo());
        curp += generarEdo(personDto.getEdo_nac().toUpperCase());
        curp += segundaLetra(personDto.getApe1().toUpperCase());
        curp += segundaLetra(personDto.getApe2().toUpperCase());
        curp += segundaLetra(personDto.getNombre().toUpperCase());
        curp += UUID.randomUUID().toString().toUpperCase().substring(0,2);
        return curp.toUpperCase();
    }

    public String generarEdo(String estado){
        String nuevo = "";
        switch (estado.toUpperCase()){
            case "AGUASCALIENTES":
                nuevo = "AS";
                break;
            case "BAJA CALIFORNIA":
                nuevo = "BC";
                break;
            case "BAJA CALIFORNIA SUR":
                nuevo = "BS";
                break;
            case "CAMPECHE":
                nuevo = "CC";
                break;
            case "CHIAPAS":
                nuevo = "CS";
                break;
            case "CHIHUAHUA":
                nuevo = "CH";
                break;
            case "CIUDAD DE MÉXICO":
                nuevo = "DF";
                break;
            case "COAHUILA":
                nuevo = "CL";
                break;
            case "COLIMA":
                nuevo = "CM";
                break;
            case "DURANGO":
                nuevo = "DG";
                break;
            case "GUANAJUATO":
                nuevo = "GT";
                break;
            case "GUERRERO":
                nuevo = "GR";
                break;
            case "HIDALGO":
                nuevo = "HG";
                break;
            case "JALISCO":
                nuevo = "JC";
                break;
            case "MÉXICO":
                nuevo = "MC";
                break;
            case "MICHOACÁN":
                nuevo = "MN";
                break;
            case "MORELOS":
                nuevo = "MS";
                break;
            case "NAYARIT":
                nuevo = "NT";
                break;
            case "NUEVO LEÓN":
                nuevo = "NL";
                break;
            case "OAXACA":
                nuevo = "OC";
                break;
            case "PUEBLA":
                nuevo = "PL";
                break;
            case "QUERÉTARO":
                nuevo = "QO";
                break;
            case "QUINTANA ROO":
                nuevo = "QR";
                break;
            case "SAN LUIS POTOSÍ":
                nuevo = "SP";
                break;
            case "SINALOA":
                nuevo = "SL";
                break;
            case "SONORA":
                nuevo = "SR";
                break;
            case "TABASCO":
                nuevo = "TC";
                break;
            case "TAMAULIPAS":
                nuevo = "TS";
                break;
            case "TLAXCALA":
                nuevo = "TL";
                break;
            case "VERACRUZ":
                nuevo = "VZ";
                break;
            case "YUCATÁN":
                nuevo = "YN";
                break;
            case "ZACATECAS":
                nuevo = "ZS";
                break;
            default:
                nuevo = "NA";
        }
        return nuevo;
    }

    public String segundaLetra(String letra){
        String segundo = "";
        for (int i = 1; i < letra.length(); i++){
            if(!"AEIOU".contains(letra.charAt(i) + "")){
                segundo = letra.charAt(i) + "";
                break;
            }
        }
        return segundo;
    }

    public String generarRFC(PersonDto personDto){
        String rfc = "";
        rfc = String.valueOf(personDto.getApe1().charAt(0));
        rfc += String.valueOf(personDto.getApe1().charAt(1));
        rfc += String.valueOf(personDto.getApe2().charAt(0));
        rfc += String.valueOf(personDto.getNombre().charAt(0));
        rfc += String.valueOf(personDto.getFecha_nac().charAt(2));
        rfc += String.valueOf(personDto.getFecha_nac().charAt(3));
        rfc += String.valueOf(personDto.getFecha_nac().charAt(5));
        rfc += String.valueOf(personDto.getFecha_nac().charAt(6));
        rfc += String.valueOf(personDto.getFecha_nac().charAt(8));
        rfc += String.valueOf(personDto.getFecha_nac().charAt(9));
        rfc += UUID.randomUUID().toString().toUpperCase().substring(0,3);

        return rfc.toUpperCase();
    }

    private String genRandomPass() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * caracteres.length());
            password.append(caracteres.charAt(index));
        }
        return password.toString();
    }


}

