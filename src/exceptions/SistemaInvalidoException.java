package exceptions;

public class SistemaInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public SistemaInvalidoException() {
		super("Erro no sistema");
	}

	public SistemaInvalidoException(String msg) {
		super(msg);
	}
}
