package DB_PRaktikum8;
//Vorher der Library ...oracle\admin\product\11.2.0\client_1\jdbc\lib\ojdbc6.jar
//von Oracle in die IDE einbinden. Die Readme im jdbc Verzeichnis enthält eine 
//genaue Treiberbeschreibung.

import java.sql.*;

/**
 * Klasse zur Verwendung eines JDBC-Datenbanktreibers von Oracle mit
 * Implementierung der entsprechenden Interfaces aus java.sql.*
 * 
 * @author Jens Lambert
 * @version 1.0
 */

public class Java_Database_Connector{

    // Instanzvariablen konfiguriert für den Gebrauch im PC-Pool Netzwerk
	
	/**
	 * Datenbankserver (Im PC-Pool: 10.50.10.12)
	 */
    private String host = "10.50.10.12"; 
    private String port = "1521";
    /**
     * Im PC-Pool dbprakt
     */
    private String sid = "dbprakt";
    private String user = "user098";
    private String password = "vuseveju";
    private String connectorString = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid;
    // Instanzvariable für den JDBC-Treiber
    // (siehe gleichnamiges Interface in API)
    private Connection con;
    // Instanzvariable für Metadatenabfrage
    // (siehe gleichnamiges Interface in API)
    private DatabaseMetaData dmd;

    /**
     * Konstruktor für das Erzeugen einer Datenbank-Connector-Instanz
     * 
     * @param _user
     *            Benutzername der Datenbank
     * @param _password
     *            zugehöriges Passwort
     * @throws SQLException
     */
    Java_Database_Connector() throws SQLException {
    	// Neue Instanz des JDBC-Treibers im Drivermanager registrieren
    	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    	// 	Verbindung zum Datenbankserver herstellen
    	this.openConnection();
    }

    /**
     * Prozedur openConnection öffnet eine neue Instanz des JDBC-Treibers und
     * stellt Verbindung zur Datenbank her
     * 
     * @throws SQLException
     */
    public void openConnection() throws SQLException {
	// falls noch keine Verbindung existiert
	if (con == null) {
	    // Neue Instanz einer Verbindung mit der Datenbank erzeugen
	    con = DriverManager.getConnection(this.connectorString, this.user,
		    this.password);
	    // Instanz zum Abrufen der Metadaten erzeugen
	    dmd = con.getMetaData();
	    System.out.println("Database Connection opened.");

	} else
	// Instanz vorhanden und Verbindung geöffnet
	if (!con.isClosed()) {
	    System.out.println("Database Connection already opened.");

	} // Instanz vorhanden aber Verbindung getrennt
	else {
	    con = DriverManager.getConnection(this.connectorString, this.user,
		    this.password);
	    // Instanz zum Abrufen der Metadaten erzeugen
	    dmd = con.getMetaData();
	    System.out.println("Database Connection reopened.");
	}
    }

    /**
     * Prozedur closeConnection beendet die Verbindung zum Datenbankserver
     * 
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
	// Wenn es noch keine Instanz zur Verbindung mit der Datenbank gibt.
	if (con == null) {
	    System.out.println("Database Connection already closed.");
	} else
	// Wenn eine Instanz zur Verbindung mit der Datenbank besteht und diese
	// nicht geschlossen ist.
	if (!con.isClosed()) {
	    this.con.close();
	    System.out.println("Database Connection closed.");
	} else
	    // Wenn eine Instanz zur Verbindung mit der Datenbank besteht und
	    // diese bereits geschlossen ist.
	    System.out.println("Database Connection already closed.");
    }

    /**
     * Prozedur printMetadata gibt einige Metadaten der initialisierten
     * Datenbankverbindung zurück.
     * 
     * @throws SQLException
     */
    public String printMetadata() throws SQLException {
	String return_value = "";

	return_value += "\nConnected with: \n";
	return_value += "DB Product:     "
		+ this.dmd.getDatabaseProductVersion() + "\n";
	return_value += "DB-URL:         " + this.dmd.getURL() + "\n";
	return_value += "Username:       " + this.dmd.getUserName() + "\n";
	return_value += "DriverVersion:  " + this.dmd.getDriverVersion() + "\n";
	return_value += "DriverName:     " + this.dmd.getDriverName() + "\n";

	return return_value;
    }

    /**
     * Übergabe der Referenz auf eine Statement Instanz für SQL Kommunikation
     * 
     * @return Statement - gibt Instanz eines Statements zurück
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException {
    	return this.con.createStatement();
    }
    
    /**
     * Erstellt ein PreparedStatement
     * 
     * @param sql SQL für das Statement mit ? als Platzhalter fuer Parameter
     * @return PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement createPreparedStatement(String sql) throws SQLException {
    	return con.prepareStatement(sql);
    }
    
    public CallableStatement createCallableStatement(String sql) throws SQLException {
    	return con.prepareCall(sql);
    }
    
    /**
     * SQL: COMMIT;
     * @throws SQLException
     */
    public void commit() throws SQLException {
    	con.commit();
    }

}