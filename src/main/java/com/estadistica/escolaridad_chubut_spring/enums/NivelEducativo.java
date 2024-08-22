package com.estadistica.escolaridad_chubut_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NivelEducativo {
    PRIMARIO("Primario", 1),
    EGB("EGB", 1),
    SECUNDARIO("Secundario", 2),
    POLIMODAL("Polimodal", 2),
    INICIAL("Inicial", 4),
    JARDIN("Jardin", 4),
    TERCIARIO("Terciario", 5),
    UNIVERSITARIO("Universitario", 6),
    FORMACION_PROFESIONAL("Formacion Profesional", 11),
    CURSO_CAPACITACION("Curso Capacitacion", 12),
    ESCUELA_DIFERENCIAL("Escuela diferencial", 13);

    private final String nombre;
    private final int codigo;

    public static NivelEducativo fromNombre(String nombre){
        for (NivelEducativo nivel : NivelEducativo.values()){
            if(nivel.getNombre().equalsIgnoreCase(nombre))
                return nivel;
        }
        throw new IllegalArgumentException("No se encontró nivel con ese nombre");
    }

    public static NivelEducativo fromCodigo(int codigo){
        for (NivelEducativo nivel : NivelEducativo.values()){
            if(nivel.getCodigo() == codigo)
                return nivel;
        }
        throw new IllegalArgumentException("No se encontró nivel con ese codigo");
    }
}
