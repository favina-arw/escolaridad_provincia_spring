package com.estadistica.escolaridad_chubut_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodigoError {
    C_2(2,"Apellido y Nombre"),
    C_4(4, "Numero de Documento"),
    C_5(5,"Fecha naci onválida o > fecha de proceso"),
    C_6(6, "Sexo distinto de F, M o X"),
    C_400(400, "No se pudo apropiar. Existe uno o más registros con los mismos datos en ADP"),
    C_410(410,"No se puede apropiar. No existe CUIL"),
    C_921(921,"Tipo Documento blanco o no-numerico"),
    C_923(923, "tipo de documento distinto de 25, 26 o 29"),
    C_924(924,"Si tipo de documente = 29 y N. Documento entre 60.000.000 y 90.000.000"),
    C_925(925,"Si Tipo Documento = 25 o 26 y N. Documento > 9.999.999"),
    C_926(926, "Documento robado"),
    C_951(951,"Fecha de fallecimiento inválida o > fecha de proceso")
    ;

    private final int codigo;
    private final String descipcion;
    private final String accion = "VERIFICAR DATOS O PRESENTAR DOCUMENTO DE IDENTIDAD EN ANSES";

    public static CodigoError fromCodigo(int codigo){
        for(CodigoError error : CodigoError.values()){
            if (error.getCodigo() == codigo){
                return error;
            }
        }
        throw new IllegalArgumentException("No se encontró error con ese código.");
    }

}
