package exceptions;

import java.io.Serializable;

public class SistemaInvalidoException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public SistemaInvalidoException() {
		super("Erro no sistema");
	}

	public SistemaInvalidoException(String msg) {
		super(msg);
	}
}
