package hotel;

import exceptions.QuartoInvalidoException;

public class Quarto {
	private String id;
	private TipoQuarto diaria;

	public Quarto(String id, TipoQuarto diaria) throws QuartoInvalidoException {
		if (id == null || id.trim().isEmpty()) {
			throw new QuartoInvalidoException("Id nao pode ser nulo ou vazio");
		}
		if (diaria != TipoQuarto.SIMPLES && diaria != TipoQuarto.LUXO
				&& diaria != TipoQuarto.PRESIDENCIAL) {
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
