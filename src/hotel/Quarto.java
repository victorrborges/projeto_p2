package hotel;

import exceptions.QuartoInvalidoException;

public class Quarto {
	private String id;
	private TipoQuarto diaria;

	public Quarto(String id, TipoQuarto diaria) throws QuartoInvalidoException {
		if (id == null || id.trim().isEmpty()) {
			throw new QuartoInvalidoException("Id nao pode ser nulo ou vazio");
		}
		if (diaria != TipoQuarto.SIMPLES && diaria != TipoQuarto.LUXO && diaria != TipoQuarto.PRESIDENCIAL) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaria == null) ? 0 : diaria.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Quarto)) {
			return false;
		}
		Quarto quarto = (Quarto) obj;
		if (quarto.getDiaria() == getDiaria() && quarto.getId().equals(getId())) {
			return true;
		} else {
			return false;
		}
	}

}
