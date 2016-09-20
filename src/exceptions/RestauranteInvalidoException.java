package exceptions;

public class RestauranteInvalidoException extends SistemaInvalidoException {

	private static final long serialVersionUID = 1L;

	public RestauranteInvalidoException() {
		super("Erro no restaurante.");
	}

	public RestauranteInvalidoException(String msg) {
		super(msg);
	}
}
