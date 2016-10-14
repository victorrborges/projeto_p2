package exceptions;

public class HotelInvalidoException extends SistemaInvalidoException {

	private static final long serialVersionUID = 1L;

	public HotelInvalidoException() {
		super("Operacao invalida Recepcao");
	}

	public HotelInvalidoException(String msg) {
		super(msg);
	}
}
