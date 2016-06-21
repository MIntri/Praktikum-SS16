package DB_PRaktikum8;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Datenhaltungsschicht, verwaltet die Mitarbeiter
 * 
 */
public class EmpManager {

	Java_Database_Connector con;
	
	public EmpManager() {
		try {
			con = new Java_Database_Connector();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Liefert alle Mitarbeiter in einer logischen Rheinfolge zurueck.
	 * 
	 * Hinweis: Verwenden Sie für die Rückgabe eine ArrayList  mit folgender deklaration: 
	 * 		List<Employee> res = new ArrayList<Employee>();
	 * @link  http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
	 * 
	 * Mehr zu Generics in der OOP Vorlesung
	 *  
	 * @return List mit allen Mitarbeitern
	 */
	public List<Employee> listEmployee() {
		List<Employee> res = new ArrayList<Employee>();
		String query ="select * from emp";
		
		try {
			Statement statement = con.getStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setUserId(rs.getString("userid"));
				emp.setFirstName(rs.getString("firstname"));
				emp.setLastName(rs.getString("lastename"));
				emp.setSalary(rs.getInt("sal"));
				emp.setStars(rs.getString("stars"));
				res.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return res;
	}
	
	/**
	 * Liefer einen Mitarbeiter anhand seiner Id zurueck
	 * 
	 * @param id Id des Mitarbeiters
	 * @return Datensatz des Mitarbeiters
	 */
	public Employee findById(int id) {
		
		String query= "Select * from emp where userid = ?";
		Employee emp = new Employee();
		try {
			PreparedStatement prpStmt = con.createPreparedStatement(query);
			prpStmt.setInt(1, id);
			ResultSet rs = prpStmt.executeQuery();
			
			emp.setId(rs.getInt("id"));
			emp.setUserId(rs.getString("userid"));
			emp.setFirstName(rs.getString("firstname"));
			emp.setLastName(rs.getString("lastename"));
			emp.setSalary(rs.getInt("sal"));
			emp.setStars(rs.getString("stars"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	/**
	 * Aktualisiert die Daten eines Mitarbeiters
	 * 
	 * Die userid soll dabei berechnet werden.
	 * Die userid soll sich aus dem ersten Buchstaben des Vornamen und den ersten sechs Buchstaben des Nachnamens zusammensetzen.
	 * 
	 * @param emp Mitarbeiter der gespeichert werden soll
	 */
	public void save(Employee emp) {
		String query ="update emp set userid = ?, firstname = ?, lastname = ?, sal = ? where id = ?";
		try {
			PreparedStatement prpStmt = con.createPreparedStatement(query);
			prpStmt.setString(1, calcUserID(emp.getFirstName(), emp.getLastName()));
			prpStmt.setString(2, emp.getFirstName());
			prpStmt.setString(3, emp.getLastName());
			prpStmt.setInt(4, emp.getSalary());
			prpStmt.setInt(5, emp.getId());
			
			prpStmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String calcUserID(String firstName, String lastName){
		String userID="";
		userID = firstName.substring(0, 1)+ lastName.substring(0, (lastName.length() < 6) ? lastName.length() : 6);
		
		return userID;
	}
	
	/**
	 * Fuegt einen Mitarbeiter in die Datenbank ein
	 * 
	 * Die userid soll dabei berechnet werden.
	 * 
	 * @param emp Neuer Mitarbeiter
	 */
	public void insert(Employee emp) {
		String query ="Insert into emp (id,userid,firstname,lastname,sal) values (?,?,?,?,?)";
		try {
			PreparedStatement prpStmt = con.createPreparedStatement(query);
			prpStmt.setInt(1, emp.getId());
			prpStmt.setString(2, calcUserID(emp.getFirstName(), emp.getLastName()));
			prpStmt.setString(3, emp.getFirstName());
			prpStmt.setString(4, emp.getLastName());
			prpStmt.setInt(5, emp.getSalary());
			
			
			prpStmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Berechnet die Sterne fuer einen Mitarbeiter
	 * 
	 * Hier soll die PL/SQL Procedure set_employee_stars zur Berechnung aufgerufen werden (siehe Anhang).
	 * Hinweis: Benutzen Sie “CALL set_employee_stars(…)“ um die Stored Procedure aufzurufen.
	 * 
	 * @param emp Mitarbeiter dessen Sterne Berechnet werden sollen
	 */
	public void delete(Employee emp) {
		String query ="delete from emp where id =?";
		try {
			PreparedStatement prpStmt = con.createPreparedStatement(query);
			prpStmt.setInt(1, emp.getId());
			prpStmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Berechnet die Sterne fuer einen Mitarbeiter
	 * 
	 * @param emp Mitarbeiter dessen Sterne Berechnet werden sollen
	 */
	public void calcStars(Employee emp) {
		try {
			CallableStatement cs = con.createCallableStatement("{call set_employee_stars(?)}");
			cs.setInt(1, emp.getId());
			cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wird beim schließen der Anwendungaufgerufen 
	 */
	public void shutdown() {
		try {
			con.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
