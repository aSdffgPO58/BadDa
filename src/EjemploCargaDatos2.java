

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;




public class EjemploCargaDatos2 {
	
	public static void main(String args[]) {
		OracleTemplate q = null;
		Properties properties = new Properties();
		try {
			properties.load(OracleTemplate.class
					.getResourceAsStream("practica1.properties"));
			q = new OracleTemplate(properties.getProperty("database.host"),
					properties.getProperty("database.port"),
					properties.getProperty("database.sid"),
					properties.getProperty("database.user"),
					properties.getProperty("database.password"));
			q.connect();
			System.out.println("Conectado a " + q);
			EjemploCargaDatos ecd = new EjemploCargaDatos(q);
			insertarOcuEstad("Estadios.txt", ecd);
			insertarEquipos("equipos.txt",ecd);
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (InstantiationException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (q != null) {
				q.disconnect();
			}
		}
	}

	private static void insertarEstad(String archivo,	EjemploCargaDatos edc){
		File fichero = new File(archivo);
		Scanner lectura;
		try{
			String linea;
			String[] datos;
			lectura = new Scanner(fichero);
			// Leemos los datos y transformamos a instr SQL
			while(lectura.hasNextLine()){
				linea=lectura.nextLine();
				datos=linea.split("\t");
				if(datos.length>1){
					String estadio=datos[0];
					String ciudad=datos[1];
					int anyo=Integer.parseInt(datos[2]);
					int capacidad=Integer.parseInt(datos[3]);
					System.out.printf("insert into estadio"
							+ "values (%s,%d,%d)%n",estadio,capacidad,anyo);
					edc.insertarEst(estadio, capacidad, anyo);
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.printf("%s%n", e.getMessage());
		}
	}
	
	private static void insertarOcuEstad(String archivo,	EjemploCargaDatos edc){
		File fichero = new File(archivo);
		Scanner lectura;
		try{
			String linea;
			String[] datos;
			lectura = new Scanner(fichero);
			// Leemos los datos y transformamos a instr SQL
			while(lectura.hasNextLine()){
				linea=lectura.nextLine();
				datos=linea.split("\t");
				if(datos.length>1){
					String estadio=datos[0];
					String ciudad=datos[1];
					int anyo=Integer.parseInt(datos[2]);
					int capacidad=Integer.parseInt(datos[3]);
					String equipo=datos[4];
					System.out.printf("insert into ocupa"
							+ "values (%s,%s,%d)%n",equipo,estadio,anyo);
					edc.insertarOcup(equipo,estadio, anyo);
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.printf("%s%n", e.getMessage());
		}
	}
	
	private static void insertarEquipos(String archivo, EjemploCargaDatos edc){
		File fichero = new File(archivo);
		Scanner lectura;
		try{
			String linea;
			String[] datos;
			lectura = new Scanner(fichero);
			// Leemos los datos y transformamos a instr SQL
			while(lectura.hasNextLine()){
				linea=lectura.nextLine();
				datos=linea.split("\t");
				if(datos.length>1){
					String nomCorto=datos[0];
					String ciudad=datos[1];
					int fundacion = Integer.parseInt(datos[2]);
					String nomHist = datos[3];
					System.out.printf("insert into equipo"
							+ "values (%s,%d,%d)%n",estadio,capacidad,anyo);
					edc.insertarEst(estadio, capacidad, anyo);
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.printf("%s%n", e.getMessage());
		}
		
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
