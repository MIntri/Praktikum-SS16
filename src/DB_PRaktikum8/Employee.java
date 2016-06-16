package DB_PRaktikum8;

/**
 * Klasse fuer eine Zeile aus der Tabelle EMP. 
 * Damit man aus Java komfortabel ein Tupel aus EMP benutzen kann.
 * 
 */
public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private String userId;
	private int salary;
	private String stars;

	/**
	 * Erzeugt einen Mitarbeiter ohne Werte
	 */
	public Employee() {}
	
	/**
	 * Erzeugt einen Mitarbeiter mit  Werten
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param userId
	 * @param salary
	 * @param stars
	 */
	public Employee(int id, String firstName, String lastName, String userId,
			int salary, String stars) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.salary = salary;
		this.stars = stars;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	/**
	 * Liefert die Id des Mitarbeiters zurueck
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Liefert den Vornamen des Mitarbeiters zurueck
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Liefert den Nachnamen des Mitarbeiters zurueck
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Liefert die UserId des Mitarbeiters zurueck,
	 * Die UserId soll besteht aus dem ersten Buchstaben des Vornamen 
	 * und den ersten sechs Buchstaben des Nachnamens.
	 * 
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Liefert das Gehalt des Mitarbeiters zurueck
	 * @return
	 */
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * Liefert die Sternebewertung des Mitarbeiters zurueck
	 * @return
	 */
	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}
	
}
