
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class EjemploCargaDatos {

	private OracleTemplate ot;

	public EjemploCargaDatos(OracleTemplate ot) {
		this.ot = ot;
	}
	
	public void insertarEst(String estadio, int capacidad, int anyo){
		ot.executeSentence("INSERT INTO estadio VALUES (?,?,?)", estadio, capacidad, anyo);
	}
	
	public void insertarOcup(String equipo, String estadio, int anyo){
		ot.executeSentence("INSERT INTO ocupa VALUES (?,?,?)",equipo, estadio, anyo);
	}

}
