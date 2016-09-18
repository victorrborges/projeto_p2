package hotel;

import exceptions.QuartoInvalidoException;

public class Quarto {
	private String id;
	private Quartos diaria;

	public Quarto(String id, Quartos diaria) throws QuartoInvalidoException {
		if (id == null || id.trim().isEmpty()) {
			throw new QuartoInvalidoException("Id nao pode ser nulo ou vazio");
		}
		if (diaria != Quartos.SIMPLES && diaria != Quartos.LUXO && diaria != Quartos.PRESIDENCIAL) {
			throw new QuartoInvalidoException("Tipo de quarto invalido");
		}
		this.id = id;
		this.diaria = diaria;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDiaria() {
		return diaria.getPreco();
	}

	public void setDiaria(double diaria) {
		this.diaria.setPreco(diaria);
	}

}
