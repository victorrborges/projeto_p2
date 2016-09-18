package hotel;

import exceptions.EstadiaInvalidaException;
import exceptions.QuartoInvalidoException;

public class Estadia {
	private Quarto quarto;
	private int qtdeDias;
	private double gastos;

	public Estadia(String id, Quartos diaria, int qtdeDias) throws EstadiaInvalidaException, QuartoInvalidoException {
		if (id == null || id.trim().isEmpty()) {
			throw new EstadiaInvalidaException("Id nao pode ser nulo ou vazio");
		}
		if (qtdeDias <= 0) {
			throw new EstadiaInvalidaException("Quantidade de dias nao pode ser menor que 1");
		}
		if (diaria != Quartos.SIMPLES && diaria != Quartos.LUXO && diaria != Quartos.PRESIDENCIAL) {
			throw new EstadiaInvalidaException("Tipo de quarto invalido");
		}
		this.quarto = new Quarto(id, diaria);
		this.qtdeDias = qtdeDias;
		this.gastos = diaria.getPreco() * this.qtdeDias;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public int getQtdeDias() {
		return qtdeDias;
	}

	public void setQtdeDias(int qtdeDias) {
		this.qtdeDias = qtdeDias;
	}

	public double getGastos() {
		return gastos;
	}

	public void setGastos(double gastos) {
		this.gastos = gastos;
	}

}
