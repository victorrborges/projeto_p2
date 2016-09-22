package hotel;

public class Quarto {
	private String id;
	private TipoQuarto diaria;

	/**
	 * Classe Quarto
	 * 
	 * @param id
	 *		Id do quarto
	 * @param diaria
	 *		Valor da diaria que varia de acordo com o tipo, Simples, Luxo
	 *      ou Presidencial
	 */

	public Quarto(String id, TipoQuarto diaria) {
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
