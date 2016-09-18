package exceptions;

public class QuartoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuartoInvalidoException() {
		super("Quarto invalido");
	}

	public QuartoInvalidoException(String msg) {
		super(msg);
	}
}
