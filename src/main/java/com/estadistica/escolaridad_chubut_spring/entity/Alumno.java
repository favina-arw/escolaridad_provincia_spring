package com.estadistica.escolaridad_chubut_spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import com.estadistica.escolaridad_chubut_spring.util.DataFormatters;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int documento;
    @Column(name = "tipo_documento")
    private int tipoDocumento;
    private int cuilt = 0;
    @Column(name = "apellido_nombre")
    private String apellidoNombre;
    private char sexo;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name = "fecha_fallecimiento")
    private Date fechaFallecimiento = null;
    private String calle;
    @Column(name = "calle_numero")
    private int calleNumero;
    private int piso;
    private String departamento;
    @Column(name = "codigo_postal")
    private int codigoPostal;
    private String localidad;
    @Column(name = "codigo_provincia")
    private int codigoProvincia;
    private String filler = "         \r\n";
    @Column(name = "estado_apropiacion")
    private int estadoAprociacion = -1;
    private Long cueAnexo;

    public String getEstadoApropiacion(){
        return "";
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "alumno_error",
            joinColumns = @JoinColumn (name = "FK_ALUMNO", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "FK_ERROR", nullable = false)
    )
    private List<Error> errores;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "alumno_escolaridad",
            joinColumns = @JoinColumn (name = "FK_ALUMNO", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "FK_ESCOLARIDAD", nullable = false)
    )
    private List<Escolaridad> escolaridades;


    @Override
    public String toString() {
        String numeroDocumento = String.valueOf(this.documento);
        String fechaDefuncion = this.fechaFallecimiento == null ? "0" : this.fechaFallecimiento.toString();

        if (numeroDocumento.length() > 8) {
            numeroDocumento.replace(".", "");
        }

        return  DataFormatters.agregarCerosAdelante(String.valueOf(this.tipoDocumento), 2) +
                DataFormatters.agregarCerosAdelante(numeroDocumento, 8) +
                DataFormatters.agregarEspaciosAlFinal(this.apellidoNombre, 40) +
                this.sexo +
                DataFormatters.agregarCerosAdelante(DataFormatters.formatearFecha(this.fechaNacimiento), 8) +
                DataFormatters.agregarCerosAdelante(fechaDefuncion, 8) +
                DataFormatters.agregarEspaciosAlFinal(this.calle, 40) +
                DataFormatters.agregarEspaciosAlFinal(this.calleNumero > 0 ? String.valueOf(this.calleNumero): "", 5) +
                DataFormatters.agregarEspaciosAlFinal(this.piso > 0 ? String.valueOf(this.piso): "", 2) +
                DataFormatters.agregarEspaciosAlFinal(this.departamento,4) +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.codigoPostal), 4) +
                DataFormatters.agregarEspaciosAlFinal(this.localidad, 25) +
                DataFormatters.agregarCerosAdelante(String.valueOf(this.codigoProvincia), 2) +
                this.filler;
    }
}
