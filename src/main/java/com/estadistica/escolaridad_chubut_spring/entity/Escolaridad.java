package com.estadistica.escolaridad_chubut_spring.entity;

import com.estadistica.escolaridad_chubut_spring.util.DataFormatters;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Escolaridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cueAnexo;
    private Long regice;
    private Long cuilt;
    @Column(name = "ciclo_lectivo")
    private int cicloLectivo;
    private int nivel;
    @Column(name = "grado_año")
    private int gradoAño;
    @Column(name = "es_educacion_oficial")
    private char esEducacionOficial = 'S';
    @Column(name = "es_alumno_regular")
    private char esAlumnoRegular = 'S';
    @Column(name = "fecha_inicio_ciclo_lectivo")
    private Date fechaInicioCicloLectivo;
    @Column(name = "nombre_curso_carrera")
    private String nombreCursoCarrera;
    @Column(name = "fecha_certificacion")
    private Date fechaCertificacion;
    @Column(name = "codigo_dependencia")
    private int codigoDependencia = 90022804;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="alumno_id")
    private Alumno alumno;

    @Override
    public String toString() {
        return DataFormatters.agregarCerosAdelante(String.valueOf(this.cueAnexo),9) + ";" +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.regice), 10) + ";" +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.cuilt), 11) + ";" +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.cicloLectivo),4) + ";" +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.nivel), 2) + ";" +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.gradoAño), 1) + ";" +
                this.esEducacionOficial + ";" +
                this.esAlumnoRegular + ";" +
                DataFormatters.agregarEspaciosAlFinal(DataFormatters.formatearFecha(this.fechaInicioCicloLectivo), 8) + ";" +
                DataFormatters.agregarEspaciosAlFinal(this.nombreCursoCarrera, 80) + ";" +
                DataFormatters.agregarCerosAdelante(DataFormatters.formatearFecha(this.fechaCertificacion), 8) + ";" +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.codigoDependencia),8) + ";" +
                "\r\n"
                ;
    }
}
