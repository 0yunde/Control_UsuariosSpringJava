package com.controlUsuario.cu.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*; //entidad, tabla , column, id

@Entity
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "nombres")
    private String nombres;
    @Getter @Setter @Column(name = "apellidos")
    private String apellidos;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "telefono")
    private Integer telefono;
    @Getter @Setter @Column(name = "cuenta")
    private String cuenta;
    @Getter @Setter @Column(name = "contrasena")
    private String contrasena;
}
