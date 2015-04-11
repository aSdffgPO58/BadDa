package practica1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;


public class EjemploCargaDatos {

	private OracleTemplate ot;
	public EjemploCargaDatos(OracleTemplate ot) {
		this.ot = ot;
	}



	public static void main(String args[]) {
		insertarOcuEstad("estadios.txt");
	}
	
	private static void insertarOcuEstad(String archivo){
		File fichero = new File(archivo);
		String tablaOcu = "ocupa",
			   tablaEst = "estadio";
		Scanner lectura;
		System.out.println("Prueba");
		try{
			lectura = new Scanner(fichero);
			System.out.printf("%s%n",lectura.nextLine());
			// Leemos los datos y transformamos a instr SQL
			while(lectura.hasNextLine()){
				System.out.printf("Llega %n");
				String nombre = lectura.next(),
					   ciudad = lectura.next();
				int fundacion = lectura.nextInt(),
					capacidad = lectura.nextInt();
				String equipo = lectura.next();
				lectura.nextLine();
				System.out.printf("insert into %s(EQUIPO, ESTADIO, ANYOOCUP)%n"
						+ "values ('%s','%s',%d)",tablaOcu,equipo,nombre,fundacion);
			}
			System.out.println("Pasa");
		}
		catch(FileNotFoundException e){
			System.out.printf("%s%n", e.getMessage());
		}
	}
	
	private void insertarEquipos(String archivo){
		
	}
	
	private void insertarResultadoAcu(String archivo){
		
	}
	
	private void insertarPartidos(String archvivo){
		
	}

	/*
	private void listarArbitros() {
		ot.executeQuery("SELECT * FROM ARBITROS_EJEMPLO");
	}

	private void creaTablaArbitros() {
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE ARBITROS_EJEMPLO(");
		sb.append("NOMBRE VARCHAR(30) PRIMARY KEY,");
		sb.append("TIPO VARCHAR(30)");
		sb.append(")");
		ot.executeSentence(sb.toString());
	}

	private void insertarArbitro(String nombre, String tipoArbitro) {
		System.out.println("Insertando "+nombre+" - "+tipoArbitro);
		ot.executeSentence("INSERT INTO ARBITROS_EJEMPLO(NOMBRE,TIPO) VALUES (?,?)", nombre, tipoArbitro);
	}
	
	private void insertarPartidos(String archivo){
		
	}
	
	*/
}
