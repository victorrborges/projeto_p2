package exceptions;

public class HospedeInvalidoException extends SistemaInvalidoException {

	private static final long serialVersionUID = 1L;

	public HospedeInvalidoException() {
		super("Hospede Invalido");
	}

	public HospedeInvalidoException(String msg) {
		super(msg);
	}
}
