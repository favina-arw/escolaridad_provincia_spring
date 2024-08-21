package com.estadistica.escolaridad_chubut_spring.entity;

import jakarta.persistence.*;

public class Escolaridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cueAnexo;
    private Long regice;
    private Long cuilt;



    @ManyToOne
    @JoinColumn(name="id_escolaridad")
    private Alumno alumno;
}
