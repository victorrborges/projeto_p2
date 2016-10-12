package hotel;

import java.io.Serializable;

public enum TipoQuarto implements Serializable{
	SIMPLES(100.0), LUXO(250.0), PRESIDENCIAL(450.0);
	private double preco;

	TipoQuarto(Double preco) {
		this.preco = preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getPreco() {
		return preco;
	}

}
