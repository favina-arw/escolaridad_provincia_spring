package com.estadistica.escolaridad_chubut_spring.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataFormatters {
    public static String agregarEspaciosAlFinal(String cadena, int longitudMaxima) {
        for (int i = cadena.length(); i < longitudMaxima; i++) {
            cadena = cadena.concat(" ");
        }
        return cadena;
    }

    public static String agregarCerosAdelante(String cadena, int longitudMaxima) {
        for (int i = cadena.length(); i < longitudMaxima; i++) {
            cadena = "0" + cadena;
        }
        return cadena;
    }

    public static String formatearFecha(Date fechaInput) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatter.format(fechaInput).replace("/", "-");
        String pattern = "dd-MM-yyyy";

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(fecha, formatter2);

        return date.toString().replace("-", "");
    }
}
