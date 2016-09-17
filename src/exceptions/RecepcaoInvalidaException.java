package exceptions;

public class RecepcaoInvalidaException extends SistemaInvalidoException {

	private static final long serialVersionUID = 1L;
	public RecepcaoInvalidaException() {
		super("Operacao invalida Recepcao");
	}
	public RecepcaoInvalidaException(String msg){
		super(msg);
	}
}
