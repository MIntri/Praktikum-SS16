package Praktikum12;
/**
 * Diese Exception wird verursacht, wenn die Argumente in Main nicht vollständig sind
 * @author Falk Schmitz
 *
 */
public class ParamException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParamException() {
		// TODO Auto-generated constructor stub
	}

	public ParamException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ParamException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ParamException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ParamException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
