package com.estadistica.escolaridad_chubut_spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
    private int cuilt;
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

    private Long cueAnexo;

    @OneToMany(mappedBy = "alumno")
    @JoinTable(
            name = "alumno_escolaridad",
            joinColumns = @JoinColumn (name = "FK_ALUMNO", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "FK_ESCOLARIDAD", nullable = false)
    )
    private List<Escolaridad> escolaridades;


    public String agregarEspaciosAlFinal(String cadena, int longitudMaxima) {
        for (int i = cadena.length(); i < longitudMaxima; i++) {
            cadena = cadena.concat(" ");
        }
        return cadena;
    }

    public String agregarCerosAdelante(String cadena, int longitudMaxima) {
        for (int i = cadena.length(); i < longitudMaxima; i++) {
            cadena = "0" + cadena;
        }
        return cadena;
    }

    public String formatearFecha(Date fechaInput) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatter.format(fechaInput).replace("/", "-");
        String pattern = "dd-MM-yyyy";

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(fecha, formatter2);

        return date.toString().replace("-", "");
    }

    @Override
    public String toString() {
        String numeroDocumento = String.valueOf(this.documento);
        String fechaDefuncion = this.fechaFallecimiento == null ? "0" : this.fechaFallecimiento.toString();

        if (numeroDocumento.length() > 8) {
            numeroDocumento.replace(".", "");
        }

        return agregarCerosAdelante(String.valueOf(this.tipoDocumento), 2) +
                agregarCerosAdelante(numeroDocumento, 8) +
                agregarEspaciosAlFinal(this.apellidoNombre, 40) +
                this.sexo +
                agregarCerosAdelante(formatearFecha(this.fechaNacimiento), 8) +
                agregarCerosAdelante(fechaDefuncion, 8) +
                agregarEspaciosAlFinal(this.calle, 40) +
                agregarEspaciosAlFinal(this.calleNumero > 0 ? String.valueOf(this.calleNumero): "", 5) +
                agregarEspaciosAlFinal(this.piso > 0 ? String.valueOf(this.piso): "", 2) +
                agregarEspaciosAlFinal(this.departamento,4) +
                agregarCerosAdelante(String.valueOf(this.codigoPostal), 4) +
                agregarEspaciosAlFinal(this.localidad, 25) +
                agregarCerosAdelante(String.valueOf(this.codigoProvincia), 2) +
                this.filler;
    }
}
