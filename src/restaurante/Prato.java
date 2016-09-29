package restaurante;

public class Prato extends Refeicao {

	/**
	 * 
	 * @param nome
	 *            Nome do prato
	 * @param preco
	 *            Preco do prato
	 * @param descricao
	 *            Descricao do prato
	 */
	public Prato(String nome, double preco, String descricao) {
		super(nome, preco, descricao);
	}

	public double calculaPreco() {
		return super.getPreco();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((super.getDescricao() == null) ? 0 : super.getDescricao()
						.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Prato)) {
			return false;
		}
		Prato prato = (Prato) obj;
		if (prato.getDescricao().equals(getDescricao())
				&& prato.getPreco() == getPreco()) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return super.getDescricao();
	}
}
