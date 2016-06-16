package DB_PRaktikum8;

import java.util.List;

import DB_PRaktikum8.EmpManager;
import DB_PRaktikum8.Employee;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * IN DIESER KLASSE SOLLEN SIE KEINE AENDERUNGEN VORNEHMEN
 * 
 * Hauptklasse der Anwendung,
 * erzeugt und verwaltet die GUI
 * 
 * @author Bjoern Salgert
 * @version 1.1
 */
public class MainApp extends Application {

	public static void main(String[] args) {
		launch(MainApp.class, args);
	}

	private StackPane root;
	
	private TextField id;
	private TextField firstName;
	private TextField lastName;
	private Text userid;
	private TextField hireDate;
	private TextField sal;
	private Text stars;
	
	private EmpManager mgr;
	
	private Employee emp;

	private ListView<Object> list;

	/**
	 * Startet die Anwendung und baut die GUI auf.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Employee Manager");
		
		root = new StackPane();
		
		list = new ListView<Object>();
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent event) {
	        	if (event.getClickCount() == 2) {
	        		Object selected = list.getSelectionModel().getSelectedItem();
	        		if (selected != null) {
	        			loadEmp((Employee)selected);
	        		}
	        	}
	        }
	    });
		
		HBox hbox = new HBox();
		hbox.getChildren().add(list);
		hbox.getChildren().add(buildForm());
		  
        root.getChildren().add(hbox);
        stage.setScene(new Scene(root, 1000, 500));
        
        try {
			mgr = new EmpManager();
			refreshList();
		} catch (Exception e) {
			e.printStackTrace();
			alert("Anwendungsfehler in der Datenhaltungsschicht\n\n"+
					"EmpManager konnte nicht erstellt werden.\n"+ e.getMessage());
		}
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                if (mgr != null) {
                	mgr.shutdown();
                }
            }
        });
        
        stage.show();
	}
	
	/**
	 * Gibt eine Warn/Fehlermelung aus.
	 * 
	 * Vorhanden, da alter JavaFx Versionen hierfuer keine eigene Funktionalität besitzen.
	 * 
	 * @param message
	 */
	private void alert(String message) {
		
		Text txt = new Text(message);
		final VBox layout = new VBox();
		layout.getChildren().add(txt); 
		Button btn = new Button();
        btn.setText("  OK  ");
        layout.setSpacing(20);
        layout.getChildren().add(btn);
        
		layout.setStyle("-fx-background-color: #ccc");
		layout.setPadding(new Insets(30));
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
            	root.getChildren().remove(layout);
            }
        });
		
		root.getChildren().add(layout);
		
	}
	
	/**
	 * Baut das Formular fuer die Detailansicht auf.
	 * 
	 * @return
	 */
	private GridPane buildForm() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Mitarbeiter bearbeiten");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("ID:");
		grid.add(userName, 0, 1);

		id = new TextField();
		grid.add(id, 1, 1);

		int rowNum = 2;
		
		Label lbl_firstName = new Label("Vorname:");
		grid.add(lbl_firstName, 0, rowNum);
		firstName = new TextField();
		grid.add(firstName, 1, rowNum);
		rowNum++;
		
		Label lbl_nachName = new Label("Nachname:");
		grid.add(lbl_nachName, 0, rowNum);
		lastName = new TextField();
		grid.add(lastName, 1, rowNum);
		rowNum++;
		
		Label lbl_userid = new Label("UserID:");
		grid.add(lbl_userid, 0, rowNum);
		userid = new Text();
		grid.add(userid, 1, rowNum);
		rowNum++;
		
		//Label lbl_hireDate = new Label("Einstellungsdatum:");
		//grid.add(lbl_hireDate, 0, rowNum);
		hireDate = new TextField();
		//grid.add(hireDate, 1, rowNum);
		//rowNum++;
		
		Label lbl_sal = new Label("Gehalt:");
		grid.add(lbl_sal, 0, rowNum);
		sal = new TextField();
		grid.add(sal, 1, rowNum);
		rowNum++;
		
		Label lbl_stars = new Label("Sterne:");
		grid.add(lbl_stars, 0, rowNum);
		stars = new Text();
		grid.add(stars, 1, rowNum);
		rowNum++;
		
		Button btn = new Button();
        btn.setText("Speichern");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                onSaveButtonClick();
            }
        });
        Button btnNew = new Button();
        btnNew.setText("Neu");
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                onNewButtonClick();
            }
        });
        Button btnInsert = new Button();
        btnInsert.setText("Einfügen");
        btnInsert.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                onInsertButtonClick();
            }
        });
        Button btnStars = new Button();
        btnStars.setText("Sterne berechnen");
        btnStars.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                onStarsButtonClick();
            }
        });
        Button btnDelete = new Button();
        btnDelete.setText("Löschen");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                onDeleteButtonClick();
            }
        });
        HBox h = new HBox();
        h.getChildren().add(btn);
        h.getChildren().add(btnNew);
        h.getChildren().add(btnInsert);
        h.getChildren().add(btnStars);
        h.getChildren().add(btnDelete);
        h.setSpacing(5);
        grid.add(h, 1, rowNum);
		
		return grid;
	}
	
	/**
	 * Wird ausgefuehrt, wenn der Speichern-Button geklickt wird
	 */
	private void onSaveButtonClick() {
		try {
			mgr.save(mapEmp());
			refreshList();
		} catch (Exception e) {
			e.printStackTrace();
			alert("Anwendungsfehler in der Datenhaltungsschicht\n\n" + e.getMessage());
		}
	}
	
	/**
	 * Wird ausgefuehrt, wenn der Einfügen-Button geklickt wird
	 */
	private void onInsertButtonClick() {
		try {
			mgr.insert(mapEmp());
			refreshList();
		} catch (Exception e) {
			e.printStackTrace();
			alert("Anwendungsfehler in der Datenhaltungsschicht\n\n" + e.getMessage());
		}
	}
	
	/**
	 * Wird ausgefuehrt, wenn der Sterne berechnen-Button geklickt wird
	 */
	private void onStarsButtonClick() {
		try {
			mgr.calcStars(mapEmp());
			refreshList();
		} catch (Exception e) {
			e.printStackTrace();
			alert("Anwendungsfehler in der Datenhaltungsschicht\n\n" + e.getMessage());
		}
	}
	
	/**
	 * Wird ausgefuehrt, wenn der Löschen-Button geklickt wird
	 */
	private void onDeleteButtonClick() {
		try {
			mgr.delete(mapEmp());
			emp = null;
			onNewButtonClick();
			refreshList();
		} catch (Exception e) {
			e.printStackTrace();
			alert("Anwendungsfehler in der Datenhaltungsschicht\n\n" + e.getMessage());
		}
	}
	
	/**
	 * Wird ausgefuehrt, wenn der Neu-Button geklickt wird
	 */
	private void onNewButtonClick() {
		emp = null;
		id.setText("");
		firstName.setText("");
		lastName.setText("");
		sal.setText("");
		hireDate.setText("");
		stars.setText("");
		userid.setText("");
	}
	
	/**
	 * Erzeugt aus den Werten der GUI-Controls einen Mitarbeiter
	 * 
	 * @return
	 */
	private Employee mapEmp() {
		// TODO hireDate
		Employee res = new Employee();
		res.setId(Integer.parseInt(id.getText()));
		res.setFirstName(firstName.getText());
		res.setLastName(lastName.getText());
		res.setSalary(Integer.parseInt(sal.getText()));
		if (emp != null) {
			res.setStars(emp.getStars());
		}
		if (emp != null) {
			res.setUserId(emp.getUserId());
		}
		return res;
	}
	
	/**
	 * Laedt einen Mitarbeiter in der Detailansicht
	 * 
	 * @param e
	 */
	private void loadEmp(Employee e) {
		this.emp = e;
		id.setText("" + e.getId());
		firstName.setText(e.getFirstName());
		lastName.setText(e.getLastName());
		//hireDate.setText(""+e.getHireDate());
		sal.setText(""+ e.getSalary());
		stars.setText(e.getStars());
		userid.setText(e.getUserId());
	}
	
	/**
	 * Aktualisiert die GUI mit Daten aus der Datenbank
	 */
	private void refreshList() {
		try {
			List<Employee> emps = mgr.listEmployee();
			ObservableList<Object> items = FXCollections.observableArrayList(emps.toArray());
			list.setItems(items);
			if (emp != null) {
				loadEmp(mgr.findById(emp.getId()));
			}
		} catch (Exception e) {
			alert("Anwendungsfehler in der Datenhaltungsschicht\n\n" + e.getMessage());
		}
	}
	
}
