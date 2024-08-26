package com.estadistica.escolaridad_chubut_spring;

import com.estadistica.escolaridad_chubut_spring.entity.Alumno;
import com.estadistica.escolaridad_chubut_spring.entity.Institucion;
import com.estadistica.escolaridad_chubut_spring.enums.Provincia;
import com.estadistica.escolaridad_chubut_spring.enums.TipoDocumentoCodigo;
import com.estadistica.escolaridad_chubut_spring.repository.AlumnoRepository;
import com.estadistica.escolaridad_chubut_spring.repository.EscolaridadRepository;
import com.estadistica.escolaridad_chubut_spring.repository.InstitucionRepository;
import com.estadistica.escolaridad_chubut_spring.util.FilesGrabber;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.text.ParseException;
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
	private final InstitucionRepository institucionRepository;
	private final EscolaridadRepository escolaridadRepository;




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
						try{
							POIFSFileSystem fs = new POIFSFileSystem(new File(archivo));
							HSSFWorkbook libro = new HSSFWorkbook(fs.getRoot(),true);
							Sheet hoja = libro.getSheetAt(0);

							String[] datosEscuela = hoja.getRow(1).getCell(0).getStringCellValue().split("-");
							Institucion institucion = new Institucion();
							institucion.setCueAnexo(Integer.parseInt(StringUtils.deleteWhitespace(datosEscuela[0])));
							institucion.setNombre(datosEscuela[1]);
							institucion.setNivel(datosEscuela[2]);
							institucion.setModalidad(datosEscuela[3]);
							//Verificar que la institución no exista!
							institucionRepository.save(institucion);


							Alumno alumno = new Alumno();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

							for (int i = 3; i <= hoja.getLastRowNum() ; i++){
								Row fila = hoja.getRow(i);

								alumno.setCueAnexo(Long.parseLong(StringUtils.left(hoja.getRow(1).getCell(0).getStringCellValue(), 9)));

								//Celda 0 -> nombre y apellido
								alumno.setApellidoNombre(fila.getCell(0).getStringCellValue().replace(",", ""));

								//Celda 1 -> Tipo Documento
								if (StringUtils.left(fila.getCell(1).getStringCellValue(),3) == "DNI" ||
										StringUtils.left(fila.getCell(1).getStringCellValue(),3).equals("DNI")){
									alumno.setTipoDocumento(TipoDocumentoCodigo.fromCodigo(29).getCodigo());
								}else if(fila.getCell(1).getStringCellValue() == "No posee" ||
										fila.getCell(1).getStringCellValue().equals("No posee")){
									alumno.setTipoDocumento(TipoDocumentoCodigo.NO_POSEE.getCodigo());
								}else{
									alumno.setTipoDocumento(TipoDocumentoCodigo.OTROS.getCodigo());
								}

								//Celda 1 -> Numero Documento
								alumno.setDocumento(Integer.parseInt(StringUtils.right(fila.getCell(1).getStringCellValue(), 8)));

								//Celda 2 -> Fecha nacimiento
								Date fecha = formatter.parse(fila.getCell(2).getStringCellValue());
								alumno.setFechaNacimiento(fecha);

								//Celda 3 -> Sexo
								alumno.setSexo(fila.getCell(3).getStringCellValue().toUpperCase().charAt(0));

								//Se rellena el resto de datos del alumno
								alumno.setCodigoProvincia(Provincia.CHUBUT.getCodigo());
								alumno.setCalle("");
								alumno.setCalleNumero(0);
								alumno.setPiso(0);
								alumno.setDepartamento("");
								alumno.setCodigoPostal(0);
								alumno.setLocalidad("");

								alumnoRepository.save(alumno);
						}

						}catch (IOException e){
							System.out.println("Problemas con apertura de archivos");
							e.printStackTrace();
						} catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
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
