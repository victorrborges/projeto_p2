package exceptions;

public class RefeicaoInvalidaException extends RestauranteInvalidoException {

	private static final long serialVersionUID = 1L;

	public RefeicaoInvalidaException() {
		super("Refeicao invalida");
	}

	public RefeicaoInvalidaException(String msg) {
		super(msg);
	}

}
