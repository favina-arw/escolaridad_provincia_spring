package com.estadistica.escolaridad_chubut_spring.entity;

import com.estadistica.escolaridad_chubut_spring.enums.CodigoError;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_emision")
    private Date fechaEmision;
    private CodigoError error;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="alumno_id")
    private Alumno alumno;

    public Error(CodigoError error) {
        this.fechaEmision = Date.from(Instant.now());
        this.error = error;
    }
}
