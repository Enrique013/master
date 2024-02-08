package mx.edu.utez.recupera.model.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class PersonBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idperson")
    private Integer idperson;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ape1")
    private String ape1;
    @Column(name = "ap2")
    private String ape2;
    @Column(name = "fecha_nac")
    private String fecha_nac;
    @Column(name = "edo_nac")
    private String edo_nac;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "curp")
    private String curp;
    @Column(name = "rfc")
    private String rfc;
    @Column (name = "password")
    private String password;
    @Column (name = "estatus")
    private boolean activo;

}
