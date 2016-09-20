package exceptions;

public class CardapioInvalidoException extends RestauranteInvalidoException {
	
	private static final long serialVersionUID = 1L;
	public CardapioInvalidoException() {
		super("Cadastro invalido.");
	}
	public CardapioInvalidoException(String msg){
		super(msg);
	}
}
