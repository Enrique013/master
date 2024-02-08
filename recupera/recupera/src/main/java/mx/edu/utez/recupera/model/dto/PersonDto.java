package mx.edu.utez.recupera.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Integer idperson;
    private String username;
    private String nombre;
    private String ape1;
    private String ape2;
    private String fecha_nac;
    private String edo_nac;
    private String sexo;
    private String curp;
    private String rfc;
    private String password;
    private boolean activo;

}
