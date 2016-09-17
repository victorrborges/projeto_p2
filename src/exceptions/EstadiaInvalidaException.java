package exceptions;

public class EstadiaInvalidaException extends SistemaInvalidoException {

	private static final long serialVersionUID = 1L;
	public EstadiaInvalidaException() {
		super("Estadia invalida");
	}
	public EstadiaInvalidaException(String msg){
		super(msg);
	}
}
