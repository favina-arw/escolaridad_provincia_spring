package com.estadistica.escolaridad_chubut_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumentoCodigo {
    NO_POSEE(-2, "NO POSEE"),
    OTROS(-1, "OTRO"),
    LIBRETA_DE_ENROLAMIENTO(25, "LIBRETA DE ENROLAMIENTO"),
    LIBRETA_CIVICA(26, "LIBRETA CÍVICA"),
    DOCUMENTO_UNICO(29, "DOCUMENTO UNICO"),
    DNI(29, "DNI");;

    private final int codigo;
    private final String tipo;

    public String getCodigoString(){
        return String.valueOf(this.codigo);
    }

    public static TipoDocumentoCodigo fromCodigo(int codigo){
        for (TipoDocumentoCodigo tipo : TipoDocumentoCodigo.values()){
            if (tipo.getCodigo() == codigo)
                return tipo;
        }
        throw new IllegalArgumentException("No se encontró ese código tipo de documento");
    }

    public static TipoDocumentoCodigo fromTipo(String s){
        for (TipoDocumentoCodigo tipo : TipoDocumentoCodigo.values()){
            if (tipo.getTipo().equalsIgnoreCase(s))
                return tipo;
        }
        throw new IllegalArgumentException("No se encontró ese tipo de documento");
    }

}
