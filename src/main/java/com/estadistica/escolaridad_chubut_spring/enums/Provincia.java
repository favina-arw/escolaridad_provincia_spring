package com.estadistica.escolaridad_chubut_spring.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Provincia {
    CAPITAL_FEDERAL(1, "CAPITAL FEDERAL"),
    BUENOS_AIRES(2, "BUENOS AIRES"),
    CATAMARCA(3,"CATAMARCA"),
    CORDOBA(4, "CORDOBA"),
    CORRIENTES(5,"CORRIENTES"),
    ENTRE_RIOS(6, "ENTRE RIOS"),
    JUJUY(7, "JUJUY"),
    LA_RIOJA(8,"LA RIOJA"),
    MENDOZA(9, "MENDOZA"),
    SALTA(10, "SALTA"),
    SAN_JUAN(11, "SAN JUAN"),
    SAN_LUIS(12, "SAN LUIS"),
    SANTA_FE(13, "SANTA FE"),
    SANTIAGO_DEL_ESTERO(14, "SANTIAGO DEL ESTERO"),
    TUCUMAN(15, "TUCUMAN"),
    CHACO(16, "CHACO"),
    CHUBUT(17, "CHUBUT"),
    FORMOSA(18, "FORMOSA"),
    LA_PAMPA(19, "LA PAMPA"),
    MISIONES(20, "MISIONES"),
    NEUQUEN(21, "NEUQUEN"),
    RIO_NEGRO(22, "RIO NEGRO"),
    SANTA_CRUZ(23, "SANTA CRUZ"),
    TIERRA_DEL_FUEGO(24, "TIERRA DEL FUEGO");

    private final Integer codigo;
    private final String nombre;

    public String getCodigoToString(){
        return this.codigo < 10? "0"+String.valueOf(this.codigo):String.valueOf(this.codigo);
    }

    public static Provincia fromNombre(String nombre){
        for (Provincia provincia : Provincia.values()){
            if(provincia.getNombre().equalsIgnoreCase(nombre))
                return provincia;
        }
        throw new IllegalArgumentException("No se encontró provincia con ese nombre");
    }

    public static Provincia fromCodigo(int codigo){
        for (Provincia provincia : Provincia.values()){
            if(provincia.getCodigo() == codigo)
                return provincia;
        }
        throw new IllegalArgumentException("No se encontró provincia con ese código");
    }

}
