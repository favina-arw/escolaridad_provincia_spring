package com.estadistica.escolaridad_chubut_spring;

import com.estadistica.escolaridad_chubut_spring.entity.Alumno;
import com.estadistica.escolaridad_chubut_spring.enums.Provincia;
import com.estadistica.escolaridad_chubut_spring.enums.TipoDocumentoCodigo;
import com.estadistica.escolaridad_chubut_spring.repository.AlumnoRepository;
import com.estadistica.escolaridad_chubut_spring.util.FilesGrabber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class EscolaridadChubutSpringApplication implements CommandLineRunner {

	private final AlumnoRepository alumnoRepository;




	public static void main(String[] args) {
		SpringApplication.run(EscolaridadChubutSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*Inicio del programa*/

		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyMMdd");
		String hoy = fechaActual.format(formato);

		Scanner inputTeclado = new Scanner(System.in);
		FilesGrabber filesGrabber = new FilesGrabber();
		boolean running = true;
		int option = 0;

		while (running){
			System.out.println("¿Qué hacemos?");
			System.out.println("1 - Cargar alumnos para Apropiación de CUIL/T");
			System.out.println("2 - Cargar Apropiaciónes de CUIL/T");
			System.out.println("3 - Descargar alumnos para la Apropiación de CUIL/T");
			System.out.println("4 - Cargar Escolaridades");
			System.out.println("5 - Descargar escolaridades para envío");
			System.out.println("-1 - Para Salir");
			option = inputTeclado.nextInt();

			switch (option){
				case 1:
					System.out.println("Ingrese la ruta donde se encuentren los archivos para la carga de Info de Alumnos");
					filesGrabber.setPathToFiles(inputTeclado.nextLine());
					List<String> archivos = filesGrabber.seleccionarArchivos();
					archivos.forEach(archivo -> {
						/* IMPLEMENTAR LÓGICA DE RECUPERACIÓN DE DATOS DE ALUMNOS DESDE EXCEL-SINIDE */
						/** ¿APACHE-POI SIMPLE O POIJI?
						 * IMPLEMENTAR ESTADO DE "NO" O "EN PROCESO" PARA SABER CUALES ALUMNOS ESTÁN EN OPERACION DE APROPIACIÓN Y CUALES NO
						 * **/

						/* GUARDAR ALUMNO EN LA BASE DE DATOS */
					});
					break;
				case 2:

					break;
				case 3:
					System.out.println("Ingrese la ruta absoluta donde se deba depositar el archivo resulante");
					String rutaDestino = inputTeclado.nextLine();
					System.out.println(rutaDestino);
					if (rutaDestino.charAt(rutaDestino.length()-1) != '/'){
						rutaDestino.concat("/");
					}

					BufferedWriter escritor = new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(rutaDestino+"/APRO.CE.XX."+hoy+".txt"),"Windows-1252"));

					List<Alumno> alumnos = alumnoRepository.findAll();

					for(Alumno alumno : alumnos){
						if(alumno.getCuilt() > 0){
							escritor.write(alumno.toString());
						}
					}

					/*---- CERRDADO DE ARCHIVO DE ESCRITURA----*/
					escritor.close();

				case -1 :
					running = false;
					break;
			}

		}
		System.out.println("¡Adios!");




		/* INICIO LOGICA DE ARMADO DE ALUMNO */
		/*
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
		 */
		/* FIN LOGICA DE ARMADO DE ALUMNO */

	}
}
