package DB_PRaktikum8;
import java.sql.*;

/**
 * Klasse zum Testen es JDBC-Treibers mit den Klassen Java_Database_Connector 
 * 
 * @author Jens Lambert
 * @version 1.1
 */
public class Test {

    /**
     * @param args - keine
     */
    public static void main(String[] args) {

	try {

	    // Benutzerdaten übergeben und Instanz für Verbindung erzeugen
	    Java_Database_Connector myJDBC = new Java_Database_Connector();

	    // Metadaten der Verbindung ausgeben
	    System.out.println(myJDBC.printMetadata());
	  
	    // Testen bzw. Nutzen der Verbindung (s.u.)
	    testConnection(myJDBC.getStatement());
	    
	    // Beenden der Verbindung mit der Datenbank
	    myJDBC.closeConnection();

	}

	// Auffangen aller Ausnahmen und Behandlung
	catch (SQLException e) {
	    System.out.println("\n*** Java Stack Trace ***\n");
	    e.printStackTrace();
	    System.out.println("\n*** SQLException caught ***\n");

	    while (e != null) {
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("Message:  " + e.getMessage());
		System.out.println("Error Code:   " + e.getErrorCode());
		e = e.getNextException();
		System.out.println("");
	    }
	}
    }

    /**
     * testConnection führt verschiedene Tests mit der übergebenen Verbindung
     * aus
     * 
     * @param _stmt
     *            Instanz eines Statements zum Transfer an die Datenbank
     * @throws SQLException
     */
    static void testConnection(Statement _stmt) throws SQLException {

	System.out.println("\nTeste DB ...\n");

	// Ausführen von jedem SQL-Statement, gibt nur einen boolschen Wert 
	// zurück, der Auskunft über den Rückgabetyp gibt
	boolean flag = _stmt
		.execute("CREATE TABLE dbsjdbc(ID INTEGER, Name VARCHAR2(15))");
	if (!flag) {
	    System.out.println("SQL-Statement executed.");
	}

	// Führt nur DML- oder DDL-Statements aus, die kein ResultSet zurück 
	// geben, z.B. INSERT, UPDATE, DELETE ...
	_stmt.executeUpdate("INSERT INTO dbsjdbc VALUES(101, 'Meyer')");
	_stmt.executeUpdate("INSERT INTO dbsjdbc VALUES(102, 'Schmitz')");
	_stmt.executeUpdate("INSERT INTO dbsjdbc VALUES(103, 'Schuster')");

	// Führt nur SQL-Querys aus, die ein ResultSet oder eine SQLExeption
	// zurück geben, z.B. SELECT ...,
	String qstr = "SELECT * FROM dbsjdbc";
	ResultSet rs = _stmt.executeQuery(qstr);
	System.out.println("ID" + "    " + "Name");
	while (rs.next()) {
	    System.out.println(rs.getInt(1) + "   " + rs.getString("Name"));
	}
	rs.close();

	flag = _stmt.execute("DROP TABLE dbsjdbc");

	// Datenbank Verbindung schließen
	_stmt.close();
	
	System.out.println("\nTest erfolgreich beendet!\n");
	
    }

}
