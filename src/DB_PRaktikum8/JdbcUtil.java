package DB_PRaktikum8;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hilfsfunktionen zum Umgang mit JDBC,
 * werden fuer das Testen verwendet.
 */
public class JdbcUtil {

	/**
	 * Oeffnet eine Datenbankverbingung und liest einen eizelnen Wert aus der Datenbank aus.
	 * 
	 * @param sql
	 * @return
	 */
	public static int selectIntVal(String sql) {
		try {
			int res = 0;
			Java_Database_Connector con = new Java_Database_Connector();
			Statement stmt = con.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
	    	if (rs.next()) {
	    		res = rs.getInt(1);
	    	} 
	    	con.closeConnection();
	    	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Anmeldungsfehler", e);
		}
	}
	
	public static int execSql(String sql) {
		try {
			int res = 0;
			Java_Database_Connector con = new Java_Database_Connector();
			Statement stmt = con.getStatement();
			stmt.executeQuery(sql);
	    	con.closeConnection();
	    	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Anmeldungsfehler", e);
		}
	}
	
}
