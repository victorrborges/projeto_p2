package hotel;

public class Estadia {
	private Quarto quarto;
	private int qtdeDias;
	private double gastos;

	/**
	 * Classe Estadia, composta por um quarto e uma quantidade de dias
	 * 
	 * @param id
	 *            Id do Quarto
	 * @param diaria
	 *            Tipo quarto
	 * @param qtdeDias
	 *            Quantidade de dias da estadia
	 */
	public Estadia(String id, String diaria, int qtdeDias) {
		TipoQuarto tipo = escolheQuarto(diaria);
		this.quarto = new Quarto(id, tipo);
		this.qtdeDias = qtdeDias;
		this.gastos = tipo.getPreco() * this.qtdeDias;
	}

	public Quarto getQuarto() {
		return quarto;
	}
	public TipoQuarto escolheQuarto(String nome){
		if(nome.equalsIgnoreCase("simples")){
			return TipoQuarto.SIMPLES;
		}else if(nome.equalsIgnoreCase("Luxo")){
			return TipoQuarto.LUXO;
		}else{
			return TipoQuarto.PRESIDENCIAL;
		}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + qtdeDias;
		result = prime * result + ((quarto == null) ? 0 : quarto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Estadia)) {
			return false;
		}

		Estadia estadia = (Estadia) obj;
		if (estadia.getQtdeDias() == getQtdeDias() && estadia.getQuarto().equals(getQuarto())) {
			return true;
		} else {
			return false;
		}
	}

}
