package exceptions;

public class PratoInvalidoException extends RestauranteInvalidoException {

	private static final long serialVersionUID = 1L;

	public PratoInvalidoException() {
		super("Prato invalido");
	}

	public PratoInvalidoException(String msg) {
		super(msg);
	}

}
