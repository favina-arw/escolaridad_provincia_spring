package com.estadistica.escolaridad_chubut_spring;

import com.estadistica.escolaridad_chubut_spring.entity.Alumno;
import com.estadistica.escolaridad_chubut_spring.enums.Provincia;
import com.estadistica.escolaridad_chubut_spring.enums.TipoDocumentoCodigo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class EscolaridadChubutSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EscolaridadChubutSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/* INICIO LOGICA DE GUARDADO DE ALUMNO */
		Alumno alumno = new Alumno();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		alumno.setCueAnexo(Long.parseLong("123456789"));
		//Si es = a 29
		alumno.setTipoDocumento(TipoDocumentoCodigo.DOCUMENTO_UNICO.getCodigo());
		//Si no, debería ir OTRO, o NO_POSEE
		alumno.setDocumento(38080929);
		alumno.setApellidoNombre("Federico Adrián Viña");
		alumno.setSexo("masculino".toUpperCase().charAt(0));
		Date fecha = formatter.parse("17/06/1994");
		alumno.setFechaNacimiento(fecha);
		alumno.setCalle("Ernesto Echave");
		alumno.setCalleNumero(474);
		alumno.setPiso(0);
		alumno.setDepartamento("");
		alumno.setCodigoPostal(9103);
		alumno.setLocalidad("Rawson");
		alumno.setCodigoProvincia(Provincia.CHUBUT.getCodigo());
		/* FIN LOGICA DE GUARDADO DE ALUMNO */

	}
}
