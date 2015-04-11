package practica1;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Clase para acceder a una BD ORACLE
 */
public class OracleTemplate {

	/**
	 * Driver para conectar con ORACLE
	 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	/**
	 * Conexion a una BD ORACLE
	 */
	private static final String CONNECTION = "jdbc:oracle:thin:@";

	/**
	 * Host o maquina donde reside la BD a la que se quiere conectar
	 */
	String host = "";
	/**
	 * Puerto del host en el que escucha el servidor de ORACLE, es decir, puerto
	 * al que hay que conectar para acceder a la BD ORACLE
	 */
	String port = "";
	/**
	 * Nombre de la instancia o BD a la que se desea conectar
	 */
	String sid = "";
	/**
	 * CaDena de caracteres con el nombre de usuario, o login, a emplear para
	 * conectarse a la BD
	 */
	String user = "";
	/**
	 * Cadena de caracteres con el password, o contraseña, a emplear para
	 * conectarse a la BD
	 */
	String password = "";
	/**
	 * Conexion con la BD
	 */
	Connection connection = null;

	/**
	 * Metodo constructor. Asigna los valores de usuario, password, host, puerto
	 * y nombre de la bd, para que posteriormente pueda hacerse la conexion
	 * 
	 * @param host
	 *            Maquina en la que esta la BD
	 * @param port
	 *            Puerto en el que escucha el servidor ORACLE
	 * @param sid
	 *            Nombre de la BD a la que se desea conectar
	 * @param user
	 *            Nombre de usuario a emplear para conectarse a la BD
	 * @param password
	 *            Password para conectarse a la BD, con el nombre de usuario
	 *            especificado
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public OracleTemplate(String host, String port, String sid, String user,
			String password) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		this.host = host;
		this.port = port;
		this.sid = sid;
		this.user = user;
		this.password = password;

		Class.forName(DRIVER).newInstance();
	}

	/**
	 * Metodo para establecer la conexion JDBC con la BD
	 * <p>
	 * 
	 * @throws SQLException
	 * 
	 * @exception Lanza
	 *                una excepcion en caso de que se produzca algun error
	 */
	public void connect() throws SQLException {
		// Estableciendo la conexion con la BD
		if (port == null) {
			connection = DriverManager.getConnection(CONNECTION + host
					+ ":1521:" + sid, user, password);
		} else {
			connection = DriverManager.getConnection(CONNECTION + host + ":"
					+ port + ":" + sid, user, password);
		}
	}

	@Override
	public String toString() {
		return CONNECTION + host + ":1521:" + sid;
	}

	/**
	 * Metodo para cerrar la conexion JDBC con la BD
	 */
	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
			connection = null;
		} catch (SQLException sqlE) {
			connection = null;
		}
	}

	/**
	 * Metodo para realizar una pregunta SQL a la BD (una sentencia SELECT)
	 * 
	 * @param sql
	 *            sentencia SQL
	 */
	public void executeQuery(String sql) {

		// Creamos una sentencia para poder usarla con la conexion que
		// tenemos abierta
		Statement stmt = null;
		try {
			System.out
					.println("---------------------------------------------------------------------------------------");
			stmt = connection.createStatement();
			// Formulamos la pregunta y obtenemos el resultado
			ResultSet rs = stmt.executeQuery(sql);

			// Convertiremos el resutlado obtenido (tabla), en una cadena de
			// caracteres
			// que en pantalla tenga aspecto de tabla...

			// Creamos la cabecera de la tabla...
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
			}
			System.out.println();
			System.out
					.println("---------------------------------------------------------------------------------------");

			// Creamos las filas de la tabla con la informacion de la tuplas
			// obtenidas
			while (rs.next()) {// Por cada tupla
				// creamos una linea con la informacion:
				for (int j = 1; j <= numberOfColumns; j++) {
					System.out.print(" " + rs.getString(j) + "\t | ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			System.out
					.println("---------------------------------------------------------------------------------------");
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	/**
	 * Metodo para ejecutar una sentencia SQL que no sea una pregunta, es decir,
	 * que no devuelva una tabla como resultado.
	 * 
	 * @param sql
	 *            sentencia SQL
	 */
	public void executeSentence(String sql) {
		Statement stmt = null;
		try {
			System.out
					.println("---------------------------------------------------------------------------------------");
			stmt = connection.createStatement();
			int resultado = stmt.executeUpdate(sql);
			System.out.println(resultado);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			System.out
					.println("---------------------------------------------------------------------------------------");
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void executeSentence(String sql, Object...params) {
		PreparedStatement stmt = null;
		try {
			System.out
					.println("---------------------------------------------------------------------------------------");
			stmt = connection.prepareStatement(sql);
			for(int i = 0; i<params.length; i++) {
				stmt.setObject(i+1, params[i]);
			}
			int resultado = stmt.executeUpdate();
			System.out.println(resultado);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			System.out
					.println("---------------------------------------------------------------------------------------");
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}